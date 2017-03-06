package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivityAboutDetailsBinding;
import com.example.smartrealitymodules.ui.base.BaseActivity;

/**
 * Created by user on 22/2/17.
 */
public class AboutDetailsActivity extends BaseActivity {
    private Context mContext;
    private TextView txtaboutustitle, txtaboutusdesc;
    private int pos;
    private ActivityAboutDetailsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Intent in = getIntent();
        pos = in.getIntExtra("pos", 0);
        getDeps().inject(this);
        renderView();
        init();
    }


    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_details);
    }

    private void init() {
        switch (pos) {
            case 0:
                binding.txtAboutusTitle.setText(getString(R.string.about_us_better_future));
//                txtaboutusdesc.loadData(getString(R.string.about_us_future), "text/html", "utf-8");
                binding.txtAboutusDesc.setText(Html.fromHtml(getString(R.string.about_us_future)));
                Linkify.addLinks(binding.txtAboutusDesc, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
                break;

            case 1:
                binding.txtAboutusTitle.setText(getString(R.string.about_us_leadership));
                binding.txtAboutusDesc.setText(Html.fromHtml(getString(R.string.about_us_leaderships)));
                Linkify.addLinks(binding.txtAboutusDesc, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
                break;

            case 2:
                binding.txtAboutusTitle.setText(getString(R.string.about_us_tc));
                binding.txtAboutusDesc.setText(Html.fromHtml(getString(R.string.about_us_terms)));
                Linkify.addLinks(binding.txtAboutusDesc, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
                break;

            case 3:
                binding.txtAboutusTitle.setText(getString(R.string.about_us_pp));
                binding.txtAboutusDesc.setText(Html.fromHtml(getString(R.string.about_us_privacy)));
                Linkify.addLinks(binding.txtAboutusDesc, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
                break;
        }
    }

}
