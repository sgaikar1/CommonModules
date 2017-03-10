package com.example.smartrealitymodules.ui.activity;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivitySplashVideoBinding;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.SplashPresenter;
import com.example.smartrealitymodules.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by user on 10/3/17.
 */

public class SplashVideoActivity extends BaseActivity {
    @Inject
    public MainModel mainModel;
    private ActivitySplashVideoBinding binding;
    private SplashPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();
    }

    private void init() {
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.splash_video);

        binding.videoViewCommon.setVideoURI(video);
        binding.videoViewCommon.requestFocus();
        binding.videoViewCommon.start();

        binding.videoViewCommon.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                startNavigation();
            }
        });
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_video);
    }

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
}
