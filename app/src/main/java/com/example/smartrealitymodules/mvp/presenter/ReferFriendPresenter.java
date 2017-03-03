package com.example.smartrealitymodules.mvp.presenter;


import android.content.Context;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.SaveReferForRewardsPostReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.ReferFriendView;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.Utils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ennur on 6/25/16.
 */
public class ReferFriendPresenter {
    private final MainModel mainModel;
    private final ReferFriendView view;
    private CompositeSubscription subscriptions;

    public ReferFriendPresenter(MainModel mainModel, ReferFriendView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }


    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void validateForm(final Context mContext, String name, String mobileno, String altMobileno, String email, String project, String dob, String address, String pincode) {


        int status = Constants.DEFAULT_STATUS;
        boolean flag = false;
        if (name.length() == 0) {
            status = Constants.NAME_EMPTY_STATUS;
        } else if (mobileno.length() == 0 || mobileno.length() < 10) {
            status = Constants.MOBILENO_EMPTY_STATUS;
        } else if (altMobileno.length() > 0 && altMobileno.length() < 10) {
            status = Constants.MOBILENO_ALTERNATE_EMPTY_STATUS;
        } else if (email.length() > 0 && ! new Utils().isEmail(email)) {
            status = Constants.EMAIL_EMPTY_STATUS;
        } else if (pincode.length() > 0 && pincode.length() < 6) {
            status = Constants.PINCODE_EMPTY_STATUS;
        } else {
            //validation successful
            flag = true;
        }

        if (flag) {
            view.apiSaveReferForRewardsPost();
        } else {
            switch (status) {
                case Constants.NAME_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_name));
                    break;
                case Constants.MOBILENO_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_valid_mobile_no));
                    break;
                case Constants.MOBILENO_ALTERNATE_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_valid_alt_mobile_no));
                    break;
                case Constants.EMAIL_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_valid_email_id));
                    break;
                case Constants.PINCODE_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_valid_pincode));
                    break;
            }
        }


    }

    public void SaveReferForRewardsPostReq(final Context mContext, SaveReferForRewardsPostReq obj, String apiname) {
        view.showWait();

        Subscription subscription = mainModel.apiSaveReferForRewardsPost(obj, apiname, new MainModel.SaveReferForRewardsPostCallback() {
            @Override
            public void onSuccess(CommonRes commonRes) {
                view.removeWait();
                view.showToast(commonRes.getMessage());
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(CommonRes commonRes, boolean flag) {
                if (flag) {
                    view.showToast(commonRes.getMessage());
                } else {
                    view.showToast(mContext.getString(R.string.data_error));
                }
            }

        });

        subscriptions.add(subscription);
    }
}
