package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.response.MyProfileRes;

/**
 * Created by user on 8/3/17.
 */
public interface UserProfileView extends BaseView{
    void setDataOnViews(MyProfileRes myProfileRes);
}
