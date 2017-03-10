package com.example.smartrealitymodules.mvp.presenter;

import android.support.v4.app.FragmentActivity;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.VerifyMobileNumberRequest;
import com.example.smartrealitymodules.models.response.VerifyMobileNumberRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.OTPCodeFragmentView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 9/3/17.
 */
public class OTPCodeFragmentPresenter {
    private final MainModel mainModel;
    private final CompositeSubscription subscriptions;
    private final OTPCodeFragmentView view;

    public OTPCodeFragmentPresenter(MainModel mainModel, OTPCodeFragmentView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void VerifyOtpOnServer() {
        view.GotoHome();
    }

    public void verifyMobileNumber(final FragmentActivity mActivity, VerifyMobileNumberRequest obj, String apiname) {
        view.showProgressDialog();

        Subscription subscription = mainModel.getVerifyMobileNumber(obj, apiname, new MainModel.GetVerifyMobileNumberCallback() {
            @Override
            public void onSuccess(VerifyMobileNumberRes verifyMobileNumberRes) {
                view.hideProgressDialog();
            }

            @Override
            public void onError(NetworkError networkError) {
                view.hideProgressDialog();
                view.onFailure(networkError.getAppErrorMessage());
                view.showToast(networkError.getAppErrorMessage());

            }

            @Override
            public void onFailure(VerifyMobileNumberRes verifyMobileNumberRes, boolean flag) {
                view.hideProgressDialog();
                if(flag){
                    view.showToast(verifyMobileNumberRes.getMessage());
                }else{
                    view.showToast(mActivity.getString(R.string.data_error));
                }
            }

        });

        subscriptions.add(subscription);
    }
}
