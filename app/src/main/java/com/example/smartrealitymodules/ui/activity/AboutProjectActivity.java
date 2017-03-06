package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.ui.adapter.CustomImagesPagerAdapter;
import com.example.smartrealitymodules.utils.AutoScrollViewPager;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

import static com.example.smartrealitymodules.R.id.txt_aboutus_project;
import static com.example.smartrealitymodules.R.id.txt_aboutus_project_desc;

/**
 * Created by user on 1/3/17.
 */
public class AboutProjectActivity extends BaseActivity {
    Context mContext;
    private String projectName;
    private String projectDesc;
    private ArrayList<ProjectDetailsRes.ProjectImage> projectImages;
    private RelativeLayout activityaboutproject;
    private TextView txtaboutusprojectdesc;
    private CircleIndicator circleindicatorprojectabout;
    private TextView txtaboutusproject;
    private AutoScrollViewPager aboutprojectautoscrollimages;
    private ArrayList<String> mBannerImages;
    private CustomImagesPagerAdapter mCustomImagesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getIntentValues();
        renderView();
        init();
        initializeBanners();
    }

    private void init() {
        txtaboutusproject.setText(projectName);
        txtaboutusprojectdesc.setText(Html.fromHtml(projectDesc));
    }

    private void renderView() {
        setContentView(R.layout.activity_about_project);
        activityaboutproject = (RelativeLayout) findViewById(R.id.activity_about_project);
         txtaboutusprojectdesc = (TextView) findViewById(txt_aboutus_project_desc);
        circleindicatorprojectabout = (CircleIndicator) findViewById(R.id.circleindicator_project_about);
        txtaboutusproject = (TextView) findViewById(txt_aboutus_project);
        aboutprojectautoscrollimages = (AutoScrollViewPager) findViewById(R.id.about_project_autoscroll_images);
    }

    private void getIntentValues() {
        Intent i = getIntent();
        projectName = i.getStringExtra(Constants.PROJECTNAME_KEY);
        projectDesc = i.getStringExtra(Constants.PROJECT_DESC_KEY);
        projectImages = i.getParcelableArrayListExtra(Constants.PROJECTIMAGE_KEY);
    }

    private void initializeBanners() {
        mBannerImages = new ArrayList<>();

        for (int i = 0; i < projectImages.size(); i++) {
            mBannerImages.add(projectImages.get(i).getFilePath());
        }

        mCustomImagesAdapter = new CustomImagesPagerAdapter(this, mBannerImages,"");

        aboutprojectautoscrollimages.setAdapter(mCustomImagesAdapter);
        aboutprojectautoscrollimages.setInterval(3000);
        circleindicatorprojectabout.setViewPager(aboutprojectautoscrollimages);
    }
}
