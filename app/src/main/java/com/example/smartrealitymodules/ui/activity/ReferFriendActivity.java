package com.example.smartrealitymodules.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.models.request.SaveReferForRewardsPostReq;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.ReferFriendPresenter;
import com.example.smartrealitymodules.mvp.view.ReferFriendView;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);


        renderView();
        setSpinner();


        imagereferfriendcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReferFriendPresenter presenter = new ReferFriendPresenter(mainModel, ReferFriendActivity.this);
                presenter.validateForm(ReferFriendActivity.this, edittextrefername.getText().toString(), edittextrefermobilenumber.getText().toString(), edittextreferaltmobilenumber.getText().toString(), edittextreferemail.getText().toString(), projectlist.get(spinreferprojects.getSelectedItemPosition()), edittextreferdob.getText().toString(), edittextreferaddress.getText().toString(), edittextreferpincode.getText().toString());

            }
        });
    }

    public  void renderView(){
        setContentView(R.layout.activity_refer_friend);
        activityreferfriend = (RelativeLayout) findViewById(R.id.activity_refer_friend);
        edittextreferpincode = (EditText) findViewById(R.id.edittext_refer_pincode);
        edittextreferaddress = (EditText) findViewById(R.id.edittext_refer_address);
        edittextreferdob = (EditText) findViewById(R.id.edittext_refer_dob);
        spinreferprojects = (Spinner) findViewById(R.id.spin_refer_projects);
        edittextreferemail = (EditText) findViewById(R.id.edittext_refer_email);
        edittextreferaltmobilenumber = (EditText) findViewById(R.id.edittext_refer_alt_mobile_number);
        imagereferfriendcontact = (ImageView) findViewById(R.id.image_refer_friend_contact);
        edittextrefermobilenumber = (EditText) findViewById(R.id.edittext_refer_mobile_number);
        edittextrefername = (EditText) findViewById(R.id.edittext_refer_name);
        btnsave = (Button) findViewById(R.id.btn_save);
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

        projectlist = new ArrayList<>();
        projectlist.add("7");
        projectlist.add("Project 2");
        projectlist.add("Project 3");
        projectlist.add("Project 4");
        ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, projectlist);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinreferprojects.setAdapter(typeAdapter);
    }

    @Override
    public void apiSaveReferForRewardsPost() {
        if (cd.isConnectingToInternet()) {

            ArrayList<SaveReferForRewardsPostReq.ReferForReward> ReferForRewards = new ArrayList<>();

            ReferForRewards.add(new SaveReferForRewardsPostReq.ReferForReward(
                    edittextrefername.getText().toString().trim(),
                    edittextrefermobilenumber.getText().toString(),
                    edittextreferaltmobilenumber.getText().toString(),
                    edittextreferemail.getText().toString(),
                    projectlist.get(spinreferprojects.getSelectedItemPosition()),
                    edittextreferdob.getText().toString(),
                    edittextreferaddress.getText().toString(),
                    edittextreferpincode.getText().toString()));
            SaveReferForRewardsPostReq obj = new SaveReferForRewardsPostReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID, ReferForRewards);

            presenter = new ReferFriendPresenter(mainModel, this);
            presenter.SaveReferForRewardsPostReq(ReferFriendActivity.this, obj, ApiNames.SaveReferForRewardsPost);
        } else {
            mUtils.toastAlert(this, getString(R.string.no_internet));
        }
    }
}
