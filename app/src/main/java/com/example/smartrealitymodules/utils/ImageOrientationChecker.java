package com.example.smartrealitymodules.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by user on 7/11/16.
 */

public class ImageOrientationChecker {

    public static Bitmap changeOrientation(String mCurrentPhotoPath, Bitmap bitmapGallery) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(mCurrentPhotoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        return rotateBitmap(bitmapGallery, orientation);
    }

    private static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteLastFromDCIM(Activity activity) {

        boolean success = false;
        try {
            File[] images = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "DCIM/Camera").listFiles();
            File latestSavedImage = images[0];
            for (int i = 1; i < images.length; ++i) {
                if (images[i].lastModified() > latestSavedImage.lastModified()) {
                    latestSavedImage = images[i];
                }
            }

            // OR JUST Use  success = latestSavedImage.delete();
            success = latestSavedImage.delete();
            activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(latestSavedImage.getPath()))));
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return success;
        }

    }
}
