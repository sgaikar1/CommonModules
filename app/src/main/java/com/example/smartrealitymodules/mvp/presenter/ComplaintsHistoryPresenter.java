package com.example.smartrealitymodules.mvp.presenter;

import android.content.Context;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.request.DeleteComplaintReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.GetComplaintsHistoryRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.ComplaintsHistoryView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 10/3/17.
 */
public class ComplaintsHistoryPresenter {
    private final MainModel mainModel;
    private final ComplaintsHistoryView view;
    private final CompositeSubscription subscriptions;

    public ComplaintsHistoryPresenter(MainModel mainModel, ComplaintsHistoryView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getComplaintsRequest(CommonReq obj, String apiname) {
        view.showWait();

        Subscription subscription = mainModel.getDisplayComplaint(obj, apiname, new MainModel.GetDisplayComplaintCallback() {
            @Override
            public void onSuccess(GetComplaintsHistoryRes displayFeedbackRes) {
                view.removeWait();
                view.getDisplayComplaints(displayFeedbackRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(GetComplaintsHistoryRes projectListingRes, boolean flag) {
                view.removeWait();
                view.showEmptyView();
            }

        });

        subscriptions.add(subscription);
    }

    public void getDeleteComplaint(final Context mContext, DeleteComplaintReq obj, String apiname) {
        view.showProgressDialog();

            Subscription subscription = mainModel.getDeleteComplaint(obj, apiname, new MainModel.GetDeleteComplaintCallback() {
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
