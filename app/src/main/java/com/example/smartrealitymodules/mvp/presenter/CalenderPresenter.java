package com.example.smartrealitymodules.mvp.presenter;

import android.app.Activity;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.CalenderView;
import com.example.smartrealitymodules.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 1/3/17.
 */
public class CalenderPresenter {
    private final MainModel mainModel;
    private final CalenderView view;

    public CalenderPresenter(MainModel mainModel, CalenderView view) {
        this.mainModel = mainModel;
        this.view = view;
    }

    public void submitDate(Activity activity, String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date todaydate = new Date();
            String modifiedDate= sdf.format(todaydate);
            todaydate = sdf.parse(modifiedDate);
            Date selectedDate = sdf.parse(date);

            if(todaydate.after(selectedDate))    {
                new Utils().toastAlert(activity, activity.getString(R.string.date_should_greater) );
            } else   {
                view.returnDateToParent(date);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void checkSelectedDayChange(int year, int month, int dayOfMonth) {
        String year1 = String.valueOf(year);
        String month1 = String.valueOf(month + 1);
        if (month1.length() == 1) {
            month1 = "0" + month1;
        }
        String day1 = String.valueOf(dayOfMonth);
        if (day1.length() == 1) {
            day1 = "0" + day1;
        }
        String date = day1 + "-" + month1 + "-" + year1;

        view.getFinalDate(date);
    }

    public String getCurrentDate() {

        Calendar calendar = Calendar.getInstance();

        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) {
            day = "0" + day;
        }

        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }

        String year = String.valueOf(calendar.get(Calendar.YEAR));

        String date = day +"-" + month + "-" +  year;
        return date;
    }
}
