package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.ui.fragments.GalleryImagesFragment;
import com.example.smartrealitymodules.ui.fragments.WalkThroughFragment;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;

/**
 * Created by user on 28/2/17.
 */
public class GalleryActivity extends BaseActivity {
    private Context mContext;
    private int position;
    private String WalkThroughURL;
    private ArrayList<ProjectDetailsRes.Architectures> architectures;
    private ArrayList<ProjectDetailsRes.Constructions> constructions;
    private LinearLayout maincontent;
    private ViewPager container;
    private AppBarLayout appbar;
    private TabLayout tabs;
    private GalleryPagerAdapter mGalleryPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        getIntentValues();
        renderView();
        init();
    }

    private void init() {
        mGalleryPagerAdapter = new GalleryPagerAdapter(getSupportFragmentManager());
        container.setAdapter(mGalleryPagerAdapter);
        container.setCurrentItem(position);
        tabs.setupWithViewPager(container);

        container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void renderView() {
        setContentView(R.layout.activity_gallery);
        maincontent = (LinearLayout) findViewById(R.id.main_content);
        container = (ViewPager) findViewById(R.id.container);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        tabs = (TabLayout) findViewById(R.id.tabs);
    }

    private void getIntentValues() {
        position = getIntent().getIntExtra(Constants.POSITION_KEY, 0);
        WalkThroughURL = getIntent().getStringExtra(Constants.WALKTHROUGH_URL_KEY);
        architectures = getIntent().getParcelableArrayListExtra(Constants.ARCHITECTURE_KEY);
        constructions = getIntent().getParcelableArrayListExtra(Constants.CUNSTRUCTION_KEY);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class GalleryPagerAdapter extends FragmentPagerAdapter {

        public GalleryPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment mFragment = null;
            switch (position) {
                case 0:
                    mFragment = new GalleryImagesFragment().newInstance(architectures, Constants.ARCHITECTURE_KEY);
                    break;
                case 1:
                    mFragment = new GalleryImagesFragment().newInstance(constructions);
                    break;
                case 2:
                    mFragment = new WalkThroughFragment().newInstance(WalkThroughURL);

                    break;
            }
            return mFragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.

            if (TextUtils.isEmpty(WalkThroughURL)) {
                return 2;
            } else {
                return 3;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Architectural";
                case 1:
                    return "Construction Updates";
                case 2:
                    return "Walkthrough";
            }
            return null;
        }
    }
}
