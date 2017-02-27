package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.share.CheckForShareRes;

/**
 * Created by user on 23/2/17.
 */
public interface ShareView extends BaseView{
    void GetCheckForShareSuccess(CheckForShareRes checkForShareReq);
}
