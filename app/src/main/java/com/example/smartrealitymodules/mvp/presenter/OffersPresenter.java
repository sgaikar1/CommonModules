package com.example.smartrealitymodules.mvp.presenter;

import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.response.GetOffersRes;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.OffersView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 13/2/17.
 */

public class OffersPresenter {
    private final MainModel mainModel;
    private final OffersView view;
    private CompositeSubscription subscriptions;

    public OffersPresenter(MainModel mainModel, OffersView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getOffersList(CommonReq obj) {
        view.showWait();

        Subscription subscription = mainModel.getOffersList(obj,new MainModel.GetOffersListCallback() {
            @Override
            public void onSuccess(GetOffersRes getOffersRes) {
                view.removeWait();
                view.getOffersListSuccess(getOffersRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }

}
