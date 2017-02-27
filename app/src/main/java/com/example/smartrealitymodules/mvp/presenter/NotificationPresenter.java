package com.example.smartrealitymodules.mvp.presenter;

import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.response.GetAllJumbleNotificationsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.NotificationView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 20/2/17.
 */
public class NotificationPresenter {
    private final MainModel mainModel;
    private final NotificationView view;
    private final CompositeSubscription subscriptions;

    public NotificationPresenter(MainModel mainModel, NotificationView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getNotificationList(CommonReq obj, String apiname) {
        view.showWait();

        Subscription subscription = mainModel.getNotificationList(obj, apiname, new MainModel.GetNotificationListCallback() {
            @Override
            public void onSuccess(GetAllJumbleNotificationsRes getAllJumbleNotificationsRes) {
                view.removeWait();
                view.getNotificationListSuccess(getAllJumbleNotificationsRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(GetAllJumbleNotificationsRes getAllJumbleNotificationsRes) {
                view.showEmptyView();
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

}
