package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.response.GetResourcesResponse;

/**
 * Created by user on 10/3/17.
 */
public interface ResourcesView extends BaseView {
    void getResourcesSuccess(GetResourcesResponse getResourcesResponse);

    void showEmptyView(GetResourcesResponse getResourcesResponse);
}
