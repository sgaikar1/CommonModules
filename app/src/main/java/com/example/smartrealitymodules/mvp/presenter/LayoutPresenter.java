package com.example.smartrealitymodules.mvp.presenter;

import android.content.Context;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.GetLayoutDetailsReq;
import com.example.smartrealitymodules.models.response.GetLayoutDetailsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.LayoutView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 2/3/17.
 */
public class LayoutPresenter {
    private final LayoutView view;
    private final MainModel mainModel;
    private final CompositeSubscription subscriptions;

    public LayoutPresenter(MainModel mainModel, LayoutView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void GetLayoutDetails(final Context mContext, GetLayoutDetailsReq obj, String apiname) {
        view.showWait();

        Subscription subscription = mainModel.getLayoutDetails(obj, apiname, new MainModel.GetLayoutDetailsCallback() {
            @Override
            public void onSuccess(GetLayoutDetailsRes getLayoutDetailsRes) {
                view.removeWait();
                view.getLayoutDetailsSuccess(getLayoutDetailsRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(GetLayoutDetailsRes getLayoutDetailsRes, boolean flag) {
                view.removeWait();
                view.showEmptyView();
                if(flag){
                    view.showToast(getLayoutDetailsRes.getMessage());
                }else{
                    view.showToast( mContext.getString(R.string.data_error));
                }
            }

            @Override
            public void onFailure(GetLayoutDetailsRes getLayoutDetailsRes) {
                view.removeWait();
                view.showEmptyView();
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
