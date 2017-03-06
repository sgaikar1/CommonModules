package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.ui.adapter.AmenitiesAdapter;
import com.example.smartrealitymodules.ui.adapter.CustomImagesPagerAdapter;
import com.example.smartrealitymodules.utils.AutoScrollViewPager;

import java.util.ArrayList;

import javax.inject.Inject;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by user on 2/3/17.
 */
public class ProjectAmenitiesActivity extends BaseActivity {
    @Inject
    public MainModel mainModel;
    private Context mContext;
    private ArrayList<ProjectDetailsRes.AmenityImage> amenitiesImages;
    private ArrayList<ProjectDetailsRes.Amenity> amenities;
    private RecyclerView recycleramenitiesdetails;
    private CircleIndicator circleindicatorprojectamenities;
    private AutoScrollViewPager autoscrollamenitiesimages;
    private ArrayList<String> amenitiesBannerImages;
    private CustomImagesPagerAdapter mAmenitiesImagesAdapter;
    private AmenitiesAdapter mAmenitiesAdapter;

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
        setContentView(R.layout.activity_project_amenities);
        recycleramenitiesdetails = (RecyclerView) findViewById(R.id.recycler_amenities_details);
         circleindicatorprojectamenities = (CircleIndicator) findViewById(R.id.circleindicator_project_amenities);
         autoscrollamenitiesimages = (AutoScrollViewPager) findViewById(R.id.autoscroll_amenities_images);
    }



    private void initializeAmenitiesBanners() {
        amenitiesBannerImages = new ArrayList<>();

        for (int i = 0; i < amenitiesImages.size(); i++) {
            amenitiesBannerImages.add(amenitiesImages.get(i).getFilePath());
        }

        mAmenitiesImagesAdapter = new CustomImagesPagerAdapter(this, amenitiesBannerImages,"");
        autoscrollamenitiesimages.setAdapter(mAmenitiesImagesAdapter);
        autoscrollamenitiesimages.startAutoScroll(3000);
        circleindicatorprojectamenities.setViewPager(autoscrollamenitiesimages);
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
        recycleramenitiesdetails.setLayoutManager(layoutManager);

        if (mAmenitiesAdapter != null) {
            int size = metrics.widthPixels / columns;
            mAmenitiesAdapter.setImageSize(size);
        }

        recycleramenitiesdetails.setAdapter(mAmenitiesAdapter);
    }
}
