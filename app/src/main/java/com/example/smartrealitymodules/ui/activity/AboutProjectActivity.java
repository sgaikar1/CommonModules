package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Html;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivityAboutProjectBinding;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.ui.adapter.CustomImagesPagerAdapter;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;

/**
 * Created by user on 1/3/17.
 */
public class AboutProjectActivity extends BaseActivity {
    Context mContext;
    private String projectName;
    private String projectDesc;
    private ArrayList<ProjectDetailsRes.ProjectImage> projectImages;
    private ArrayList<String> mBannerImages;
    private CustomImagesPagerAdapter mCustomImagesAdapter;
    private ActivityAboutProjectBinding binding;

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
        binding.txtAboutusProject.setText(projectName);
        binding.txtAboutusProjectDesc.setText(Html.fromHtml(projectDesc));
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_project);
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

        binding.aboutProjectAutoscrollImages.setAdapter(mCustomImagesAdapter);
        binding.aboutProjectAutoscrollImages.setInterval(3000);
        binding.circleindicatorProjectAbout.setViewPager(binding.aboutProjectAutoscrollImages);
    }
}
