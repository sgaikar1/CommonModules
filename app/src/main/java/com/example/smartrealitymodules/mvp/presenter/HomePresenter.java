package com.example.smartrealitymodules.mvp.presenter;


import com.example.smartrealitymodules.models.CityListResponse;
import com.example.smartrealitymodules.mvp.view.HomeView;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.mvp.model.MainModel;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ennur on 6/25/16.
 */
public class HomePresenter {
    private final MainModel mainModel;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(MainModel mainModel, HomeView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList() {
        view.showWait();

        Subscription subscription = mainModel.getCityList(new MainModel.GetCityListCallback() {
            @Override
            public void onSuccess(CityListResponse cityListResponse) {
                view.removeWait();
                view.getityListSuccess(cityListResponse);
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
