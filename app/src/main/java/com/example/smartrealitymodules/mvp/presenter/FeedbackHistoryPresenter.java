package com.example.smartrealitymodules.mvp.presenter;

import android.content.Context;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.request.DeleteFeedbackReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.DisplayFeedbackRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.FeedbackHistoryView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 9/3/17.
 */
public class FeedbackHistoryPresenter {
    private final MainModel mainModel;
    private final FeedbackHistoryView view;
    private final CompositeSubscription subscriptions;

    public FeedbackHistoryPresenter(MainModel mainModel, FeedbackHistoryView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getDisplayFeedback(CommonReq obj, String apiname) {
        view.showWait();

        Subscription subscription = mainModel.getDisplayFeedback(obj, apiname, new MainModel.GetDisplayFeedbackCallback() {
            @Override
            public void onSuccess(DisplayFeedbackRes displayFeedbackRes) {
                view.removeWait();
                view.getDisplayFeedBack(displayFeedbackRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(DisplayFeedbackRes projectListingRes, boolean flag) {
                view.removeWait();
                view.showEmptyView();
            }

        });

        subscriptions.add(subscription);
    }

    public void getDeleteFeedback(final Context mContext, DeleteFeedbackReq obj, String apiname) {
        view.showProgressDialog();

        Subscription subscription = mainModel.getDeleteFeedback(obj, apiname, new MainModel.GetDeleteFeedbackCallback() {
            @Override
            public void onSuccess(CommonRes commonRes) {
                view.hideProgressDialog();
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
