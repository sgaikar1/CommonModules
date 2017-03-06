package com.example.smartrealitymodules.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by vijay on 28/11/16.
 */
public class DateUtils {

    public final String dd_mmm_yyyy_hh_mm_ss = "dd-mmm-yyyy hh:mm:ss";
    public final String yyyy_MM_dd = "yyyy-MM-dd";
    public final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd hh:mm:ss";
    public final String dd_MM_yyyy_HH_mm_ss = "dd-MM-yyyy hh:mm:ss";
    public final String yyyy_MM_dd_HH_mm_ss_ampm = "yyyy-MM-dd hh:mm:ss a";
    public final String dd_MM_yyyy_HH_mm_ss_ampm = "dd-MM-yyyy hh:mm:ss a";
    public final String dd_slash_MM_yyyy_HH_mm_ss_ampm = "dd/MM/yyyy hh:mm:ss a";
    public final String dd_slash_MMM_yyyy_HH_mm_ss_ampm = "dd MMM yyyy hh:mm:ss a";
    public final String dd_MMMM_yyyy = "dd MMMM yyyy";
    public final String dd_MMM_yyyy = "dd MMM yyyy";
    public final String dd_MMM_yy = "dd MMM yy";
    public final String dd_MM_yyyy = "dd-MM-yyyy";
    public final String yyyy_MM_dd_hh_mm_ss = "yyyy-MM-dd hh:mm:ss";
    public final String HH_mm_ss = "hh:mm:ss";
    public final String dd__MM__yyyy = "dd/MM/yyyy";

    public String changeDateFormat(String selectedDate, String oldFormat, String newFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(oldFormat, Locale.US);
        simpleDateFormat = convertDateToUTC(simpleDateFormat);
        SimpleDateFormat sdfTarget = new SimpleDateFormat(newFormat, Locale.US);
        String StrDateNew = "";
        Utils.logMe("Testing", "inside");

        try {
            Date datenw = simpleDateFormat.parse(selectedDate);
            StrDateNew = sdfTarget.format(datenw);
        } catch (ParseException e) {
            StrDateNew = "";
        }
        return StrDateNew;
    }

    public String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(time);
        String date = DateFormat.format(dd_MM_yyyy, cal).toString();
        return date;
    }

    public String getCurrentDate(Context activity) {
        String formattedDate = "";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyy_MM_dd_hh_mm_ss);
            formattedDate = simpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            formattedDate = "";
        }
        return formattedDate;
    }

    public Date parseDateFormat(String selectedDate, String format) {
        Date formattedDate = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat = convertDateToUTC(simpleDateFormat);
            formattedDate = simpleDateFormat.parse(selectedDate);
        } catch (ParseException e) {
            formattedDate = new Date();
        }
        return formattedDate;
    }

    public String convertTimeToString(Date selectedDate) {
        String finalTime = selectedDate.getHours() + ":" + selectedDate.getMinutes() + ":" + selectedDate.getSeconds();
        return finalTime;
    }

    public String convertDateeeToString(Date selectedDate) {
        String finalDate = selectedDate.getYear() + "-" + selectedDate.getMonth() + "-" +
                selectedDate.getDate();
        return finalDate;
    }

    public SimpleDateFormat convertDateToUTC(SimpleDateFormat simpleDateFormat) {
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }
}
