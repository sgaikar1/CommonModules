package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivityFeedbackDetailsBinding;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by user on 9/3/17.
 */
public class FeedbackDetailsActivity extends BaseActivity{
    private Context mContext;
    @Inject
    public MainModel mainModel;
    private ActivityFeedbackDetailsBinding binding;
    private String title,date,type,desc;

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
        type = getIntent().getStringExtra("type");
        desc = getIntent().getStringExtra("desc");
    }

    private void init() {
        binding.textFeedbackTitle.setText(title);
        binding.textFeedbackDate.setText(date);
        binding.textFeedbackType.setText(type);
        binding.textFeedbackDescription.setText(desc);
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feedback_details);
    }
}
