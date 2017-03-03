package com.example.smartrealitymodules.ui.activity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.models.request.ScheduleSiteVisitReq;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.ScheduleSiteVisitPresenter;
import com.example.smartrealitymodules.mvp.view.ScheduleSiteVisitView;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by user on 1/3/17.
 */
public class ScheduleSiteVisitActivity extends BaseActivity implements View.OnClickListener, ScheduleSiteVisitView {
    private Context mContext;
    @Inject
    public MainModel mainModel;
    private String projectId;
    private String projectName;
    private String projectCity;
    private RelativeLayout activityschedulesitevisit;
    private FrameLayout crouton;
    private LinearLayout llpickuphome;
    private EditText edittextcity, edittextaddress, edittextsettime, edittextscheduletime, edittextscheduledate, edittextselectprojectname;
    private RelativeLayout rlpickUp;
    private CheckBox checkboxpickUp;
    private Button btnapply;
    private int siteIntentReq = 10;
    private String date="";
    private int noOfTimesCalled=0;
    private ScheduleSiteVisitPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);

        getIntentValues();
        renderView();
        init();
    }


    private void getIntentValues() {

        Intent i = getIntent();
        projectId = i.getStringExtra(Constants.PROJECTID_KEY);
        projectName = i.getStringExtra(Constants.PROJECTNAME_KEY);
        projectCity = i.getStringExtra(Constants.PROJECT_CITY_KEY);
    }

    private void renderView() {
        setContentView(R.layout.activity_schedule_site_visit);
        btnapply = (Button) findViewById(R.id.btn_apply);
        activityschedulesitevisit = (RelativeLayout) findViewById(R.id.activity_schedule_site_visit);
        llpickuphome = (LinearLayout) findViewById(R.id.ll_pickup_home);
        edittextcity = (EditText) findViewById(R.id.edittext_city);
        edittextaddress = (EditText) findViewById(R.id.edittext_address);
        edittextsettime = (EditText) findViewById(R.id.edittext_set_time);
        rlpickUp = (RelativeLayout) findViewById(R.id.rl_pickUp);
        checkboxpickUp = (CheckBox) findViewById(R.id.checkbox_pickUp);
        edittextscheduletime = (EditText) findViewById(R.id.edittext_schedule_time);
        edittextscheduledate = (EditText) findViewById(R.id.edittext_schedule_date);
        edittextselectprojectname = (EditText) findViewById(R.id.edittext_select_project_name);
        setProgressBar();
        removeWait();
    }

    private void init() {

        presenter = new ScheduleSiteVisitPresenter(mainModel, ScheduleSiteVisitActivity.this);

        edittextscheduledate.setOnClickListener(this);
        edittextscheduletime.setOnClickListener(this);
        edittextsettime.setOnClickListener(this);
        btnapply.setOnClickListener(this);

        edittextselectprojectname.setText(projectName);
        edittextcity.setText(projectCity);

        rlpickUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_pickUp:
                if (checkboxpickUp.isChecked()) {
                    llpickuphome.setVisibility(View.INVISIBLE);
                    checkboxpickUp.setChecked(false);
                } else {
                    llpickuphome.setVisibility(View.VISIBLE);
                    checkboxpickUp.setChecked(true);
                }
                break;
            case R.id.edittext_schedule_date:
                Intent sitevist = new Intent(ScheduleSiteVisitActivity.this, CalenderActivity.class);
                startActivityForResult(sitevist, siteIntentReq);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.edittext_schedule_time:
                edittextscheduletime.setText("");
                noOfTimesCalled = 0;
                Calendar scheduleTime = Calendar.getInstance();
                int schedulehour = scheduleTime.get(Calendar.HOUR_OF_DAY);
                int scheduleminute = scheduleTime.get(Calendar.MINUTE);
                TimePickerDialog scheduleTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                       presenter.pickTime(ScheduleSiteVisitActivity.this,noOfTimesCalled,selectedHour,selectedMinute,
                               edittextscheduledate.getText().toString());
                        noOfTimesCalled++;
                    }
                }, schedulehour, scheduleminute, true);//Yes 24 hour time
                scheduleTimePicker.setTitle("Select Time");
                scheduleTimePicker.show();
                break;
            case R.id.edittext_set_time:
                edittextsettime.setText("");
                noOfTimesCalled = 0;
                Calendar setTime = Calendar.getInstance();
                int sethour = setTime.get(Calendar.HOUR_OF_DAY);
                int setminute = setTime.get(Calendar.MINUTE);
                TimePickerDialog setTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        presenter.setPickUpTime(ScheduleSiteVisitActivity.this,noOfTimesCalled,selectedHour,selectedMinute,
                                edittextscheduletime.getText().toString(), edittextscheduledate.getText().toString());
                    }
                }, sethour, setminute, true);//Yes 24 hour time
                setTimePicker.setTitle("Select Time");
                setTimePicker.show();

                break;

            case R.id.btn_apply:
                if (cd.isConnectingToInternet()) {
                    boolean callApi = true;

                    presenter.validateForm(ScheduleSiteVisitActivity.this,edittextscheduledate.getText().toString(),edittextscheduletime.getText().toString(),checkboxpickUp.isChecked(),
                            edittextsettime.getText().toString(),edittextaddress.getText().toString() );



                } else {
                    mUtils.toastAlert(this, getString(R.string.no_internet));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == siteIntentReq) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                date = data.getStringExtra(Constants.DATE_KEY);
                edittextscheduledate.setText(date);
                edittextscheduletime.setText("");
                edittextsettime.setText("");
            }
        }
    }

    @Override
    public void setScheduleTime(String time) {
        edittextscheduletime.setText(time);
    }

    @Override
    public void setPickUpTime(String time) {
        edittextsettime.setText(time);

    }

    @Override
    public void showSuccess(boolean checked) {
        if (checked) {
            ScheduleSiteVisitReq obj = new ScheduleSiteVisitReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID,
                    projectId, "sitevisit", edittextscheduledate.getText().toString()+" "+edittextscheduletime.getText().toString(), "true", edittextsettime.getText().toString(),
                    edittextaddress.getText().toString(), "");
            presenter.getScheduleSiteVisit(mContext,obj, ApiNames.ScheduleSiteVisit);
        } else {
            ScheduleSiteVisitReq obj = new ScheduleSiteVisitReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID,
                    projectId, "sitevisit", edittextscheduledate.getText().toString()+" "+edittextscheduletime.getText().toString(), "false", edittextsettime.getText().toString(),
                    edittextaddress.getText().toString(), "");

            presenter.getScheduleSiteVisit(mContext,obj, ApiNames.ScheduleSiteVisit);
        }

    }
}
