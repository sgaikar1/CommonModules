package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivityProjectAmenitiesBinding;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.ui.adapter.AmenitiesAdapter;
import com.example.smartrealitymodules.ui.adapter.CustomImagesPagerAdapter;
import com.example.smartrealitymodules.ui.base.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by user on 2/3/17.
 */
public class ProjectAmenitiesActivity extends BaseActivity {
    @Inject
    public MainModel mainModel;
    private Context mContext;
    private ArrayList<ProjectDetailsRes.AmenityImage> amenitiesImages;
    private ArrayList<ProjectDetailsRes.Amenity> amenities;
    private ArrayList<String> amenitiesBannerImages;
    private CustomImagesPagerAdapter mAmenitiesImagesAdapter;
    private AmenitiesAdapter mAmenitiesAdapter;
    private ActivityProjectAmenitiesBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);

        getIntentValues();

        renderView();
        initializeAmenitiesBanners();
        initializeAmenities();
    }

    public void getIntentValues() {
        Intent i = getIntent();
        amenitiesImages = i.getParcelableArrayListExtra("AmenityImages");
        amenities = i.getParcelableArrayListExtra("Amenities");
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project_amenities);
    }


    private void initializeAmenitiesBanners() {
        amenitiesBannerImages = new ArrayList<>();

        for (int i = 0; i < amenitiesImages.size(); i++) {
            amenitiesBannerImages.add(amenitiesImages.get(i).getFilePath());
        }

        mAmenitiesImagesAdapter = new CustomImagesPagerAdapter(this, amenitiesBannerImages, "");
        binding.autoscrollAmenitiesImages.setAdapter(mAmenitiesImagesAdapter);
        binding.autoscrollAmenitiesImages.startAutoScroll(3000);
        binding.circleindicatorProjectAmenities.setViewPager(binding.autoscrollAmenitiesImages);
    }

    private void initializeAmenities() {


        mAmenitiesAdapter = new AmenitiesAdapter(this, amenities);
        orientationBasedUI(getResources().getConfiguration().orientation);

        initializeAmenitiesBanners();
    }

    private void orientationBasedUI(int orientation) {
        final WindowManager windowManager = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int columns = orientation == Configuration.ORIENTATION_PORTRAIT ? 3 : 5;

        GridLayoutManager layoutManager = new GridLayoutManager(this, columns);
        binding.recyclerAmenitiesDetails.setLayoutManager(layoutManager);

        if (mAmenitiesAdapter != null) {
            int size = metrics.widthPixels / columns;
            mAmenitiesAdapter.setImageSize(size);
        }

        binding.recyclerAmenitiesDetails.setAdapter(mAmenitiesAdapter);
    }
}
