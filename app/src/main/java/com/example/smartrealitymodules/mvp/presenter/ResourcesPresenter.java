package com.example.smartrealitymodules.mvp.presenter;

import android.content.Context;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.GetResourceRequest;
import com.example.smartrealitymodules.models.response.GetResourcesResponse;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.ResourcesView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 10/3/17.
 */
public class ResourcesPresenter {
    private final CompositeSubscription subscriptions;
    private final MainModel mainModel;
    private final ResourcesView view
            ;

    public ResourcesPresenter(MainModel mainModel, ResourcesView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void apiGetResources(final Context mContext, GetResourceRequest obj, String apiname) {
        view.showWait();

        Subscription subscription = mainModel.getResources(obj, apiname, new MainModel.GetResourcesCallback() {
            @Override
            public void onSuccess(GetResourcesResponse getResourcesResponse) {
                view.removeWait();
                view.getResourcesSuccess(getResourcesResponse);
                view.showToast(getResourcesResponse.getMessage());
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(GetResourcesResponse getResourcesResponse, boolean flag) {
                view.removeWait();
                view.showEmptyView(getResourcesResponse);
                if(flag){
                    view.showToast(getResourcesResponse.getMessage());
                }else{
                    view.showToast(mContext.getResources().getString(R.string.no_internet));
                }
            }

        });

        subscriptions.add(subscription);
    }
}
