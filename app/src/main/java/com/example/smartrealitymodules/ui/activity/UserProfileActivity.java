package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityUserProfileBinding;
import com.example.smartrealitymodules.models.request.MyProfileReq;
import com.example.smartrealitymodules.models.response.MyProfileRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.UserProfilePresenter;
import com.example.smartrealitymodules.mvp.view.UserProfileView;
import com.example.smartrealitymodules.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by user on 3/3/17.
 */

public class UserProfileActivity extends BaseActivity implements UserProfileView, View.OnClickListener {
    @Inject
    public MainModel mainModel;
    private Context mContext;
    private ActivityUserProfileBinding binding;
    private UserProfilePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);

        renderView();
        apiMyProfile();
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
        binding.btnEdit.setOnClickListener(this);
    }

    private void apiMyProfile() {
        if (isConnected) {

            MyProfileReq obj = new MyProfileReq(userId, userType);
            presenter = new UserProfilePresenter(mainModel, this);
            presenter.getMyProfile(mContext,obj, ApiNames.MyProfile);

        } else {
            mUtils.toastAlert(this, getString(R.string.no_internet));
            // TODO: 8/3/17 Load from database
//            setOfflineData(dBhelper.getValuesFromModule(dBhelper.MYPROFILE));
        }
    }

    @Override
    public void setDataOnViews(MyProfileRes myProfileRes) {
        binding.setResult(myProfileRes.getResult());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(UserProfileActivity.this, EditUserProfileActivity.class);
        startActivity(intent);
    }
}
