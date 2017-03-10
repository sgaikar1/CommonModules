package com.example.smartrealitymodules.mvp.presenter;

import android.content.Context;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.InsertUpdateFeedbackReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.FeedBackView;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 9/3/17.
 */
public class FeedbackPresenter {
    private final MainModel mainModel;
    private final CompositeSubscription subscriptions;
    private final FeedBackView view;

    public FeedbackPresenter(MainModel mainModel, FeedBackView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void validateFeedback(Context mContext, ArrayList<String> id, int selectedItemPosition, String title, String desc) {
        int status = Constants.DEFAULT_STATUS;
        boolean flag = false;
        if (title.length() == 0) {
            status = Constants.TITLE_EMPTY_STATUS;
        } else if (desc.length() == 0) {
            status = Constants.DESCRIPTION_EMPTY_STATUS;
        } else {
            //validation successful
            flag = true;

        }

        if (flag) {
            if(id != null && id.size() > 0) {
                view.apiInsertUpdateFeedback(id.get(selectedItemPosition));
            }

        } else {
            switch (status) {
                case Constants.TITLE_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_title));
                    break;
                case Constants.DESCRIPTION_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_desc));
                    break;
            }
        }
    }

    public void getInsertUpdateFeedback(final Context mContext, InsertUpdateFeedbackReq obj, String apiname) {
        view.showProgressDialog();

        Subscription subscription = mainModel.getInsertUpdateFeedback(obj, apiname, new MainModel.GetInsertUpdateFeedbackCallback() {
            @Override
            public void onSuccess(CommonRes commonRes) {
                view.hideProgressDialog();
                view.finishActivity();
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
