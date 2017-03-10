package com.example.smartrealitymodules.ui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by user on 8/3/17.
 */
public class CameraAndGalleryBottomSheetDialogFragment extends BottomSheetDialogFragment{
    String mCurrentPhotoPath = "";
    SetImagePreviewInterface mInterface;
    int REQUEST_CODE_TAKE_PICTURE = 1;
    int REQUEST_CODE_GALLERY = 2;
    String CAPTURE_IMAGE_FILE_PROVIDER = "com.example.smartrealitymodules.fileprovider";
    Utils mUtils;

    public static Fragment newInstance(Activity context) {
        Bundle b = new Bundle();
        return Fragment.instantiate(context, CameraAndGalleryBottomSheetDialogFragment.class.getName(), b);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mInterface = (SetImagePreviewInterface) activity;

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet_camera_gallery_picker, null);
        dialog.setContentView(contentView);
        mUtils = new Utils();
        RelativeLayout rl_bottom_sheet_camera = (RelativeLayout) contentView.findViewById(R.id.rl_bottom_sheet_camera);
        RelativeLayout rl_bottom_sheet_gallery = (RelativeLayout) contentView.findViewById(R.id.rl_bottom_sheet_gallery);

        rl_bottom_sheet_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dispatchTakePictureIntent();
            }
        });

        rl_bottom_sheet_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_GALLERY);
            }
        });

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "JPEG_" + "DEFAULT" + "_";
        File storageDir = getContext().getFilesDir();

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        CAPTURE_IMAGE_FILE_PROVIDER,
                        photoFile);
                List<ResolveInfo> resolvedIntentActivities = getContext().getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
                    String packageName = resolvedIntentInfo.activityInfo.packageName;

                    getContext().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PICTURE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TAKE_PICTURE) {
            if (resultCode == Activity.RESULT_OK) {
                File imageFile = new File(mCurrentPhotoPath);
                mInterface.getImagePreviewCamera(imageFile);
                dismiss();
            }
        } else if (requestCode == REQUEST_CODE_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {

                try {
                    File path = new File(getContext().getFilesDir(), "images_upload");
                    if (!path.exists()) path.mkdirs();
                    File imageFile = new File(path, "image" + mUtils.getCurrentDate() + ".jpg");

                    InputStream inputStream = getActivity().getContentResolver().openInputStream(
                            data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            imageFile);
                    mUtils.copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();

                    mInterface.getImagePreviewCamera(imageFile);
                    dismiss();
                } catch (Exception e) {

                }
            }
        }

    }

    public interface SetImagePreviewInterface {

        public void getImagePreviewCamera(File file);

    }
}

