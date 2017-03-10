package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivityOtpBinding;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.ui.fragments.OTPCodeFragment;
import com.example.smartrealitymodules.ui.fragments.OTPMobileNumberFragment;

/**
 * Created by user on 8/3/17.
 */
public class OtpActivity extends BaseActivity{
    private Context mContext;
    private ActivityOtpBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =this;
        renderView();
        init();
    }

    private void init() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        Fragment mFrag = new OTPMobileNumberFragment(new OTPMobileNumberFragment.FragmentReplace() {
            @Override
            public void onClickReplaceFragment(String otpMobileNumber) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                Bundle data = new Bundle();
                data.putString("mobile", otpMobileNumber);

                Fragment mFrag = new OTPCodeFragment();
                mFrag.setArguments(data);

                ft.replace(R.id.frm_otp, mFrag).commit();
            }
        });

        ft.replace(R.id.frm_otp, mFrag).commit();
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp);

    }
}
