package com.example.smartrealitymodules.mvp.presenter;

import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.share.CheckForShareReq;
import com.example.smartrealitymodules.models.share.CheckForShareRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.ShareView;

import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 23/2/17.
 */
public class SharePresenter {
    private final MainModel mainModel;
    private final ShareView view;
    private final CompositeSubscription subscriptions;

    public SharePresenter(MainModel mainModel, ShareView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCheckForShare(ArrayList<CheckForShareReq> mySoapArr, String apiname) {
        view.showWait();

        Subscription subscription = mainModel.GetCheckForShare(mySoapArr, apiname, new MainModel.GetCheckForShareCallback() {
            @Override
            public void onSuccess(CheckForShareRes checkForShareRes) {
                view.removeWait();
                view.GetCheckForShareSuccess(checkForShareRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(CheckForShareRes checkForShareRes) {
                view.GetCheckForShareSuccess(checkForShareRes);
            }
        });
        subscriptions.add(subscription);
    }

}
