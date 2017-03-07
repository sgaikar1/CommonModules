package com.example.smartrealitymodules.mvp.view;

/**
 * Created by user on 1/3/17.
 */
public interface ScheduleSiteVisitView extends BaseView {
    void setScheduleTime(String time);

    void setPickUpTime(String time);

    void showSuccess(boolean checked);

    void finishActivity();
}
