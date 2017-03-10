package com.example.smartrealitymodules.mvp.presenter;

import android.content.Context;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.MyProfileReq;
import com.example.smartrealitymodules.models.response.MyProfileRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.UserProfileView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 8/3/17.
 */
public class UserProfilePresenter {
    private final CompositeSubscription subscriptions;
    private final MainModel mainModel;
    private final UserProfileView view;

    public UserProfilePresenter(MainModel mainModel, UserProfileView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getMyProfile(final Context mContext, MyProfileReq obj, String apiname) {
        view.showProgressDialog();

        Subscription subscription = mainModel.getMyProfile(obj, apiname, new MainModel.GetMyProfileCallback() {
            @Override
            public void onSuccess(MyProfileRes myProfileRes) {
                view.hideProgressDialog();
                view.setDataOnViews(myProfileRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.hideProgressDialog();
                view.onFailure(networkError.getAppErrorMessage());
                view.showToast(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(MyProfileRes myProfileRes, boolean flag) {
                view.hideProgressDialog();
                if(flag){
                    view.showToast(myProfileRes.getMessage());
                }else{
                    view.showToast( mContext.getString(R.string.data_error));
                }
            }

        });

        subscriptions.add(subscription);
    }
}
