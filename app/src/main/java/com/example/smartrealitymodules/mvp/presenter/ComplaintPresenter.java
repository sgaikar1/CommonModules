package com.example.smartrealitymodules.mvp.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.InsertUpdateComplaintRequestReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.ComplaintView;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.ImageOrientationChecker;
import com.example.smartrealitymodules.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 10/3/17.
 */
public class ComplaintPresenter {
    private final MainModel mainModel;
    private final ComplaintView view;
    private final CompositeSubscription subscriptions;

    public ComplaintPresenter(MainModel mainModel, ComplaintView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void validateComplaint(Context mContext, String title, String desc) {
        int status = Constants.DEFAULT_STATUS;
        boolean flag = false;
        if (title.length() == 0) {
            status = Constants.TITLE_EMPTY_STATUS;
        } else if (desc.length() == 0) {
            status = Constants.DESCRIPTION_EMPTY_STATUS;
        } else {
            flag = true;

        }

        if (flag) {
                view.apiInsertUpdateFeedback(title,desc);

        } else {
            switch (status) {
                case Constants.TITLE_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_title));
                    break;
                case Constants.DESCRIPTION_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_desc));
                    break;
            }
        }
    }

    public void convertFileToBitmap(File file) {
        try {

            Bitmap originalBitmap = BitmapFactory.decodeFile(file.getPath());
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            final int maxSize = 1080;
            int outWidth;
            int outHeight;
            int inWidth = originalBitmap.getWidth();
            int inHeight = originalBitmap.getHeight();
            if (inWidth > inHeight) {
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;
            }
            Bitmap processedImage = Bitmap.createScaledBitmap(originalBitmap, outWidth, outHeight, true);
            Bitmap orientedImage = ImageOrientationChecker.changeOrientation(file.getPath(), processedImage);

            processedImage = orientedImage;

            //Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            processedImage.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();

            Uri tmpURI = Uri.fromFile(file);

            String base64 = Base64.encodeToString(bitmapdata, Base64.NO_WRAP);
            new Utils().logMe("base64", base64);

            view.setImage(orientedImage, base64);
        } catch (Exception e) {
            new Utils().logMe("Exception camera", e.toString());
        }
    }

    public void getInsertUpdateComplaint(final Context mContext, InsertUpdateComplaintRequestReq obj, String apiname) {
        view.showProgressDialog();

        Subscription subscription = mainModel.getInsertUpdateComplaint(obj, apiname, new MainModel.GetInsertUpdateComplaintCallback() {
            @Override
            public void onSuccess(CommonRes commonRes) {
                view.hideProgressDialog();
                view.finishActivity();
                view.showToast(commonRes.getMessage());
            }

            @Override
            public void onError(NetworkError networkError) {
                view.hideProgressDialog();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(CommonRes commonRes, boolean flag) {
                view.hideProgressDialog();
                if(flag){
                    view.showToast(commonRes.getMessage());
                }else{
                    view.showToast(mContext.getResources().getString(R.string.no_internet));
                }
            }

        });

        subscriptions.add(subscription);
    }
}
