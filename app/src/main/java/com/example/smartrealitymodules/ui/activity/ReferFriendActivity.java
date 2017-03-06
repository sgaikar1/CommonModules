package com.example.smartrealitymodules.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityReferFriendBinding;
import com.example.smartrealitymodules.models.request.SaveReferForRewardsPostReq;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.ReferFriendPresenter;
import com.example.smartrealitymodules.mvp.view.ReferFriendView;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

public class ReferFriendActivity extends BaseActivity implements ReferFriendView {

    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    @Inject
    public MainModel mainModel;
    private RelativeLayout activityreferfriend;
    private EditText edittextreferpincode, edittextreferaddress, edittextreferdob, edittextreferemail, edittextreferaltmobilenumber, edittextrefermobilenumber, edittextrefername;
    private Spinner spinreferprojects;
    private ImageView imagereferfriendcontact;
    private Button btnsave;
    private ArrayList<String> projectlist;
    private ReferFriendPresenter presenter;
    private ActivityReferFriendBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);


        renderView();
        setSpinner();

       presenter = new ReferFriendPresenter(mainModel, ReferFriendActivity.this);

    }


    public  void renderView(){

        binding = DataBindingUtil.setContentView(this, R.layout.activity_refer_friend);
        binding.setReferActivity(this);
        setProgressBar();
    }

    private void setSpinner() {

        // TODO: 20/2/17 for just demo i am using String xml
        // TODO: 20/2/17 uncomment following code and connect to database and fetch project list from db
        /*String res = dBhelper.getValuesFromModule(dBhelper.HOMEPAGE);
        if (res != null && res.length() > 0) {

            HomePageRes data = new Gson().fromJson(res, HomePageRes.class);

            if (data.getResult().getProjects() != null && !data.getResult().getProjects().isEmpty()) {
                idlist = new ArrayList<>();
                projectlist = new ArrayList<>();

                for (HomePageRes.Result.Project project : data.getResult().getProjects()) {
                    idlist.add(project.getProjectID());
                    projectlist.add(project.getProjectName());
                }

                ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, projectlist);
                typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinReferProjects.setAdapter(typeAdapter);
            }
        }*/

        // TODO: 6/3/17 spinner needs data from database, database not used yet, hence not implemented using databinding..
        projectlist = new ArrayList<>();
        projectlist.add("7");
        projectlist.add("Project 2");
        projectlist.add("Project 3");
        projectlist.add("Project 4");
        ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, projectlist);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinReferProjects.setAdapter(typeAdapter);
    }

    public void openDatePicker(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(ReferFriendActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        presenter.setDate(year, monthOfYear, dayOfMonth);

                    }
                }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    public void startContactPicker(){
        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
    }

    public void validate(){
        presenter.validateForm(ReferFriendActivity.this, binding.edittextReferName.getText().toString(), binding.edittextReferMobileNumber.getText().toString(), binding.edittextReferAltMobileNumber.getText().toString(), binding.edittextReferEmail.getText().toString(), projectlist.get(binding.spinReferProjects.getSelectedItemPosition()), binding.edittextReferDob.getText().toString(), binding.edittextReferAddress.getText().toString(), binding.edittextReferPincode.getText().toString());
    }


    @Override
    public void apiSaveReferForRewardsPost() {
        if (isConnected) {

            ArrayList<SaveReferForRewardsPostReq.ReferForReward> ReferForRewards = new ArrayList<>();

            ReferForRewards.add(new SaveReferForRewardsPostReq.ReferForReward(
                    binding.edittextReferName.getText().toString().trim(),
                    binding.edittextReferMobileNumber.getText().toString(),
                    binding.edittextReferAltMobileNumber.getText().toString(),
                    binding.edittextReferEmail.getText().toString(),
                    projectlist.get(binding.spinReferProjects.getSelectedItemPosition()),
                    binding.edittextReferDob.getText().toString(),
                    binding.edittextReferAddress.getText().toString(),
                    binding.edittextReferPincode.getText().toString()));
            SaveReferForRewardsPostReq obj = new SaveReferForRewardsPostReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID, ReferForRewards);

            presenter = new ReferFriendPresenter(mainModel, this);
            presenter.SaveReferForRewardsPostReq(ReferFriendActivity.this, obj, ApiNames.SaveReferForRewardsPost);
        } else {
            mUtils.toastAlert(this, getString(R.string.no_internet));
        }
    }

    @Override
    public void setDate(String year, String month, String day) {
        binding.edittextReferDob.setText(day + "-" + month + "-"
                + year);
    }
}
