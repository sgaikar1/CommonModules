package com.example.smartrealitymodules.mvp.view;

import android.graphics.Bitmap;

import com.example.smartrealitymodules.models.response.MyProfileRes;

/**
 * Created by user on 8/3/17.
 */
public interface EditUserProfileView extends BaseView{
    void setDataOnViews(MyProfileRes myProfileRes);

    void setImage(Bitmap orientedImage, String base64);

    void finishActivity();

    void apiEditProfileReq();

    void setUserDob(String dob);

    void setAnniversary(String anniversary);
}
