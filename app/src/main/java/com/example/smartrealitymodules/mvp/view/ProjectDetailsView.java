package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;

/**
 * Created by user on 27/2/17.
 */
public interface ProjectDetailsView extends BaseView {
    void getProjectDetailsSuccess(ProjectDetailsRes projectDetailsRes);

    void showEmptyView();

    void getProjectInterestedIn(CommonRes commonRes);

    void resetInterestedImage();
}
