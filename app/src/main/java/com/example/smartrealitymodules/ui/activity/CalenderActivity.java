package com.example.smartrealitymodules.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RelativeLayout;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.CalenderPresenter;
import com.example.smartrealitymodules.mvp.view.CalenderView;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import javax.inject.Inject;

/**
 * Created by user on 1/3/17.
 */
public class CalenderActivity extends BaseActivity implements CalenderView {
    @Inject
    public MainModel mainModel;
    private Context mContext;
    private RelativeLayout activitycalender;
    private Button btnsubmit;
    private CalendarView calsitevisit;
    private String date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);

        renderView();
        init();
    }

    private void init() {
        final CalenderPresenter presenter = new CalenderPresenter(mainModel, CalenderActivity.this);

        date = presenter.getCurrentDate();


        calsitevisit.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                presenter.checkSelectedDayChange(year, month, dayOfMonth);

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.submitDate(CalenderActivity.this, date);

            }
        });

    }

    private void renderView() {
        setContentView(R.layout.activity_calender);
        activitycalender = (RelativeLayout) findViewById(R.id.activity_calender);
        btnsubmit = (Button) findViewById(R.id.btn_submit);
        calsitevisit = (CalendarView) findViewById(R.id.cal_site_visit);
    }

    @Override
    public void returnDateToParent(String selectedDate) {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATE_KEY, selectedDate);
        setResult(Activity.RESULT_OK, intent);
        finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    @Override
    public void getFinalDate(String date) {
        this.date = date;
    }
}
