package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.response.GetAllJumbleNotificationsRes;

/**
 * Created by user on 20/2/17.
 */
public interface NotificationView extends BaseView {
    void getNotificationListSuccess(GetAllJumbleNotificationsRes getOffersRes);

    void showEmptyView();
}
