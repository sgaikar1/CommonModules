package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivityAboutUsBinding;
import com.example.smartrealitymodules.ui.base.BaseActivity;

/**
 * Created by user on 22/2/17.
 */

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private LinearLayout linearaboutprivacy, linearaboutterms, linearaboutleadership, linearaboutfuture;
    private ActivityAboutUsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);
        renderView();
        initClicks();
    }


    public void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_us);

    }

    private void initClicks() {
        binding.linearAboutPrivacy.setOnClickListener(this);
        binding.linearAboutTerms.setOnClickListener(this);
        binding.linearAboutLeadership.setOnClickListener(this);
        binding.linearAboutFuture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_about_future:
                Intent future = new Intent(AboutUsActivity.this, AboutDetailsActivity.class);
                future.putExtra("pos", 0);
                startActivity(future);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;

            case R.id.linear_about_leadership:
                Intent leadership = new Intent(AboutUsActivity.this, AboutDetailsActivity.class);
                leadership.putExtra("pos", 1);
                startActivity(leadership);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;

            case R.id.linear_about_terms:
                Intent terms = new Intent(AboutUsActivity.this, AboutDetailsActivity.class);
                terms.putExtra("pos", 2);
                startActivity(terms);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;

            case R.id.linear_about_privacy:
                Intent privacy = new Intent(AboutUsActivity.this, AboutDetailsActivity.class);
                privacy.putExtra("pos", 3);
                startActivity(privacy);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
        }
    }
}
