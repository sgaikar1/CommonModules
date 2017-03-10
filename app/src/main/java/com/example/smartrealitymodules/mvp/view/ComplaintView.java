package com.example.smartrealitymodules.mvp.view;

import android.graphics.Bitmap;

/**
 * Created by user on 10/3/17.
 */
public interface ComplaintView extends BaseView{
    void apiInsertUpdateFeedback(String title, String desc);

    void setImage(Bitmap orientedImage, String base64);

    void finishActivity();
}
