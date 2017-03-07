package com.example.smartrealitymodules.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.ScheduleSiteVisitReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.ScheduleSiteVisitView;
import com.example.smartrealitymodules.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 1/3/17.
 */
public class ScheduleSiteVisitPresenter {
    private final MainModel mainModel;
    private final ScheduleSiteVisitView view;
    private final CompositeSubscription subscriptions;

    public ScheduleSiteVisitPresenter(MainModel mainModel, ScheduleSiteVisitView view) {
        this.mainModel = mainModel;
        this.view= view;
        this.subscriptions = new CompositeSubscription();
    }

    public void pickTime(Activity activity, int noOfTimesCalled, int selectedHour, int selectedMinute, String scheduledDate) {
        if (noOfTimesCalled == 0) {
            String hour = String.valueOf(selectedHour), min = String.valueOf(selectedMinute);
            if (selectedHour < 10) {
                hour = "0" + String.valueOf(selectedHour);
            }

            if (selectedMinute < 10) {
                min = "0" + String.valueOf(selectedMinute);
            }
            if (scheduledDate.trim().length() > 0) {
                SimpleDateFormat sdfdate = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date today = new Date();
                Date selecteddate = null;

                try {
                    selecteddate = sdfdate.parse(scheduledDate + " " + hour + ":" + min);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                sdfdate.setLenient(false);

                if (selecteddate.before(today)) {
                    new Utils().toastAlert(activity, activity.getString(R.string.schedule_time_should_greater));
                } else {
                    view.setScheduleTime(hour + ":" + min);
                }
            } else {
                new Utils().toastAlert(activity, activity.getString(R.string.select_site_visit_schedule_date));
            }
        }
    }

    public void setPickUpTime(Activity activity, int noOfTimesCalled, int selectedHour, int selectedMinute, String scheduledTime, String scheduledDate) {
        if (noOfTimesCalled == 0) {
//                        updateTime(edittextSetTime, selectedHour,selectedMinute);
            String hour = String.valueOf(selectedHour), min = String.valueOf(selectedMinute);
            if (selectedHour < 10) {
                hour = "0" + String.valueOf(selectedHour);
            }

            if (selectedMinute < 10) {
                min = "0" + String.valueOf(selectedMinute);
            }
//                        edittextSetTime.setText(hour + ":" + min);

            if (scheduledTime.trim().length() > 0) {
                String[] date = scheduledTime.trim().split(":");

                SimpleDateFormat sdfdate = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date today = new Date();
                Date selecteddate = null;

                try {
                    selecteddate = sdfdate.parse(scheduledDate + " " + hour + ":" + min);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                sdfdate.setLenient(false);

                if (selecteddate.before(today)) {
                    new Utils().toastAlert(activity, activity.getString(R.string.schedule_time_should_greater));
                }else {
                    if ((Integer.parseInt(date[0]) < Integer.parseInt(hour)) || (Integer.parseInt(date[0]) == Integer.parseInt(hour) && (Integer.parseInt(date[1]) < Integer.parseInt(min)))) {
                        new Utils().toastAlert(activity, activity.getString(R.string.pickup_time_less_than_schedule_time));
                    } else {
                        view.setPickUpTime(hour + ":" + min);
                    }
                }
            } else {
                new Utils().toastAlert(activity, activity.getString(R.string.select_site_visit_schedule_time));
            }
            noOfTimesCalled++;
        }
    }

    public void validateForm(Activity activity, String scheduleDate, String scheduleTime, boolean checked, String setTime, String address) {
        if (scheduleDate.length() == 0) {
            view.showToast(activity.getString(R.string.select_site_visit_schedule_date));
        } else if (scheduleTime.length() == 0) {
            view.showToast(activity.getString(R.string.select_site_visit_schedule_time));
        } else {
            if (checked) {
                if (setTime.length() == 0) {
                    view.showToast(activity.getString(R.string.select_site_visit_set_time));
                } else if (address.length() == 0) {
                    view.showToast(activity.getString(R.string.enter_address));
                }else{
                    view.showSuccess(checked);
                }
            }else {
                view.showSuccess(checked);
            }
        }
    }

    public void getScheduleSiteVisit(final Context mContext, ScheduleSiteVisitReq obj, String apiname) {
        view.showWait();

        Subscription subscription = mainModel.getScheduleSiteVisit(obj, apiname, new MainModel.GetScheduleSiteVisitCallback() {
            @Override
            public void onSuccess(CommonRes commonRes) {
                view.removeWait();
                view.showToast(commonRes.getMessage());
                view.finishActivity();
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(CommonRes commonRes ,boolean flag) {
                view.removeWait();
                if(flag){
                    view.showToast(commonRes.getMessage());
                }else{
                    view.showToast( mContext.getString(R.string.data_error));
                }
            }

        });

        subscriptions.add(subscription);
    }
}
