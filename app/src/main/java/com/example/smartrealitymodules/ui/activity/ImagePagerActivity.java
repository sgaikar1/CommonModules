package com.example.smartrealitymodules.ui.activity;

/**
 * Created by user on 28/2/17.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.ui.fragments.ImageFragment;

import java.util.ArrayList;

public class ImagePagerActivity extends FragmentActivity {

    private ViewPager viewPager;
    ArrayList<String> list;
    int position=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        viewPager = (ViewPager) findViewById(R.id.classviewPager);

        list = getIntent().getStringArrayListExtra("ImagesList");
        position = getIntent().getIntExtra("position",0);

        if(list !=null) {
            viewPager.setAdapter(new ProductPageAdapter(
                    getSupportFragmentManager(), list));
        }
        viewPager.setCurrentItem(position);
    }


    private class ProductPageAdapter extends FragmentPagerAdapter {
        ArrayList<String> list;

        public ProductPageAdapter(FragmentManager fm, ArrayList<String> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int pos) {
            return new ImageFragment().newInstance(list.get(pos), pos, getCount());
        }

        @Override
        public int getCount() {
            return list.size();
        }

    }
}