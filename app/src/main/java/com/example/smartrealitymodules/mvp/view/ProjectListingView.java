package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.ProjectListingRes;

/**
 * Created by user on 27/2/17.
 */
public interface ProjectListingView extends BaseView {
    void getProjectListSuccess(ProjectListingRes projectListingRes);

    void showEmptyView();

    void removeRefresh();

    void getProjectInterestedIn(CommonRes commonRes);

    void resetInterestedImage();
}
