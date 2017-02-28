package com.example.smartrealitymodules.mvp.presenter;

import android.content.Context;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.ProjectDetailsReq;
import com.example.smartrealitymodules.models.request.ProjectInterestedInReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.ProjectDetailsView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 27/2/17.
 */
public class ProjectDetailsPresenter {
    private final MainModel mainModel;
    private final ProjectDetailsView view;
    private final CompositeSubscription subscriptions;

    public ProjectDetailsPresenter(MainModel mainModel, ProjectDetailsView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getProjectDetails(ProjectDetailsReq obj, String apiname) {
        view.showWait();

        Subscription subscription = mainModel.getProjectDetails(obj, apiname, new MainModel.GetProjectDetailsCallback() {
            @Override
            public void onSuccess(ProjectDetailsRes projectDetailsRes) {
                view.removeWait();
                view.getProjectDetailsSuccess(projectDetailsRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(ProjectDetailsRes projectDetailsRes) {
                view.removeWait();
                view.showEmptyView();
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void getProjectInterestedIn(final Context mContext, ProjectInterestedInReq obj, String apiname) {

        Subscription subscription = mainModel.getProjectInterestedIn(obj, apiname, new MainModel.GetProjectInterestedInCallback() {
            @Override
            public void onSuccess(CommonRes commonRes) {
                view.getProjectInterestedIn(commonRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(CommonRes commonRes, boolean flag) {
                view.resetInterestedImage();
                if (flag) {
                    view.showToast(commonRes.getMessage());
                } else {
                    view.showToast(mContext.getResources().getString(R.string.server_unavailable));
                }
            }

        });

        subscriptions.add(subscription);

    }
}