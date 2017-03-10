package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivityComplaintDetailsBinding;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by user on 10/3/17.
 */
public class ComplaintDetailsActivity extends BaseActivity {
    @Inject
    public MainModel mainModel;
    private Context mContext;
    private ActivityComplaintDetailsBinding binding;
    private String title,date,status,desc,image;

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
        title = getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("date");
        status = getIntent().getStringExtra("status");
        desc = getIntent().getStringExtra("desc");
        image = getIntent().getStringExtra("image");
    }

    private void init() {
        mUtils.loadImageInImageview(ComplaintDetailsActivity.this, image, binding.imageComplaintDetail);
        binding.textComplaintDetailTitle.setText(title);
        binding.textComplaintDetailDate.setText(date);
        binding.textComplaintDetailStatus.setText(status);
        binding.textComplaintDetailDescription.setText(desc);

        if(status.equalsIgnoreCase("Close")){
            binding.textComplaintDetailStatus.setBackgroundResource(R.drawable.rounded_light_red);
        }else{
            binding.textComplaintDetailStatus.setBackgroundResource(R.drawable.rounded_light_green);
        }
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complaint_details);
    }
}
