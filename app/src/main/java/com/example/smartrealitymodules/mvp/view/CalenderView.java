package com.example.smartrealitymodules.mvp.view;

/**
 * Created by user on 1/3/17.
 */
public interface CalenderView extends BaseView{
    void returnDateToParent(String selectedDate);

    void getFinalDate(String date);

}
