package com.example.smartrealitymodules.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivitySplashFadeInBinding;
import com.example.smartrealitymodules.models.request.SendMISCountReq;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.SplashPresenter;
import com.example.smartrealitymodules.mvp.view.SplashView;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import javax.inject.Inject;

/**
 * Created by user on 10/3/17.
 */

public class SplashFadeInActivity extends BaseActivity implements Animation.AnimationListener, SplashView {
    @Inject
    public MainModel mainModel;
    private ActivitySplashFadeInBinding binding;
    private Animation splashAnim;
    private SplashPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();
    }

    private void init() {
        presenter = new SplashPresenter(mainModel,this);

        splashAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        splashAnim.setAnimationListener(SplashFadeInActivity.this);
        binding.relativeSplash.startAnimation(splashAnim);

        if (userId.equalsIgnoreCase("")) {
            apiSendMISCount();
        }

    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_fade_in);
    }

    @Override
    public void startNavigation() {

        if (userId.equalsIgnoreCase("")) {
            //open login activity

            /*Intent loginIntent = new Intent(SplashFadeInActivity.this, LoginActivity.class);
            loginIntent.putExtra(Utils.USER_TYPE, Utils.Prospect);
            startActivity(loginIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*/
            finish();
        } else {
            //open Dashboard Activity

            /*Intent dashBoard = new Intent(SplashActivity.this, DashBoardActivity.class);
            startActivity(dashBoard);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*/
            finish();
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        presenter.startNavigation(animation,splashAnim);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void apiSendMISCount() {
        if (isConnected) {

            String mobileNumber = Constants.MOBILE_NO;// TODO: 10/3/17 use from sharedpref of your application
            String uniqueDeviceID = mUtils.getUniqueIdOfDevice();
            String emailID = mUtils.getUserDeviceEmailID(this);

            SendMISCountReq obj = new SendMISCountReq(Constants.PROJECTCODE, userType, null, mobileNumber,
                    mUtils.Devicedownload, uniqueDeviceID, mUtils.Device, emailID);

            presenter.getSendMISCount(obj, ApiNames.SendMISCount);

        }
    }
}
