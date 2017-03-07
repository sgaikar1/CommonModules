package com.example.smartrealitymodules.ui.activity;

/**
 * Created by user on 28/2/17.
 */

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ActivityImagePagerBinding;
import com.example.smartrealitymodules.ui.fragments.ImageFragment;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;

public class ImagePagerActivity extends FragmentActivity {

    ArrayList<String> list;
    int position=0;
    private ActivityImagePagerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_image_pager);

        list = getIntent().getStringArrayListExtra(Constants.IMAGELIST_KEY);
        position = getIntent().getIntExtra(Constants.POSITION_KEY,0);

        if(list !=null) {
            binding.classviewPager.setAdapter(new ProductPageAdapter(
                    getSupportFragmentManager(), list));
        }
        binding.classviewPager.setCurrentItem(position);
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