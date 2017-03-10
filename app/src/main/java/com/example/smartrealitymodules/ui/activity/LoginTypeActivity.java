package com.example.smartrealitymodules.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivityLoginTypeBinding;
import com.example.smartrealitymodules.ui.base.BaseActivity;

/**
 * Created by user on 8/3/17.
 */

public class LoginTypeActivity extends BaseActivity implements View.OnClickListener {
    private ActivityLoginTypeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderView();

    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login_type);

        binding.btnBroker.setOnClickListener(this);
        binding.btnBuyer.setOnClickListener(this);
        binding.btnEmployee.setOnClickListener(this);
        binding.btnProspect.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_broker:
                // TODO: 9/3/17 remaining
                break;
            case R.id.btn_buyer:
                // TODO: 9/3/17 remaining
                break;
            case R.id.btn_employee:
                // TODO: 9/3/17 remaining
                break;
            case R.id.btn_prospect:
                Intent intent = new Intent(LoginTypeActivity.this,OtpActivity.class);
                startActivity(intent);
                break;
        }

    }
}
