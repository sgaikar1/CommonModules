package com.example.smartrealitymodules.mvp.presenter;

import android.support.v4.app.FragmentActivity;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.VerifyMobileNumberRequest;
import com.example.smartrealitymodules.models.response.VerifyMobileNumberRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.OTPMobileNumberFragmentView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 9/3/17.
 */
public class OTPMobileNumberFragmentPresenter {
    private final MainModel mainModel;
    private final OTPMobileNumberFragmentView view;
    private final CompositeSubscription subscriptions;

    public OTPMobileNumberFragmentPresenter(MainModel mainModel, OTPMobileNumberFragmentView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void validateNumber(String number) {
        if (!number.equalsIgnoreCase("+91 ")) {
            if (number.length() == 14) {
                String otp_mobile_number = number;
                otp_mobile_number = otp_mobile_number.substring(4);

                view.validatedNumber(otp_mobile_number);
            } else {
                view.showToast("Invalid mobile number");
            }
        } else {
            view.showToast("Please enter mobile number");
        }
    }

    public void verifyMobileNumber(final FragmentActivity mActivity, VerifyMobileNumberRequest obj, String apiname) {
        view.showProgressDialog();

        Subscription subscription = mainModel.getVerifyMobileNumber(obj, apiname, new MainModel.GetVerifyMobileNumberCallback() {
            @Override
            public void onSuccess(VerifyMobileNumberRes verifyMobileNumberRes) {
                view.hideProgressDialog();
                view.navigateToVerifyOtpScreen();
            }

            @Override
            public void onError(NetworkError networkError) {
                view.hideProgressDialog();
                view.onFailure(networkError.getAppErrorMessage());
                view.showToast(networkError.getAppErrorMessage());

                // TODO: 9/3/17 remove after correct webserice url passed
                view.navigateToVerifyOtpScreen();
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
