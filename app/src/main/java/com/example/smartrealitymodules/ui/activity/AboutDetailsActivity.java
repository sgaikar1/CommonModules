package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.ui.base.BaseActivity;

/**
 * Created by user on 22/2/17.
 */
public class AboutDetailsActivity extends BaseActivity {
    private Context mContext;
    private TextView txtaboutustitle, txtaboutusdesc;
    private int pos;

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
        setContentView(R.layout.activity_about_details);
        txtaboutusdesc = (TextView) findViewById(R.id.txt_aboutus_desc);
        txtaboutustitle = (TextView) findViewById(R.id.txt_aboutus_title);
    }

    private void init() {
        switch (pos) {
            case 0:
                txtaboutustitle.setText(getString(R.string.about_us_better_future));
//                txtaboutusdesc.loadData(getString(R.string.about_us_future), "text/html", "utf-8");
                txtaboutusdesc.setText(Html.fromHtml(getString(R.string.about_us_future)));
                Linkify.addLinks(txtaboutusdesc, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
                break;

            case 1:
                txtaboutustitle.setText(getString(R.string.about_us_leadership));
                txtaboutusdesc.setText(Html.fromHtml(getString(R.string.about_us_leaderships)));
                Linkify.addLinks(txtaboutusdesc, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
                break;

            case 2:
                txtaboutustitle.setText(getString(R.string.about_us_tc));
                txtaboutusdesc.setText(Html.fromHtml(getString(R.string.about_us_terms)));
                Linkify.addLinks(txtaboutusdesc, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
                break;

            case 3:
                txtaboutustitle.setText(getString(R.string.about_us_pp));
                txtaboutusdesc.setText(Html.fromHtml(getString(R.string.about_us_privacy)));
                Linkify.addLinks(txtaboutusdesc, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
                break;
        }
    }

}
