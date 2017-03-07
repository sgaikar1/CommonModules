package com.example.smartrealitymodules.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.CustomImagesBinding;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;

/**
 * Created by user on 1/3/17.
 */
public class CustomImagesPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Integer> mImages;
    private ArrayList<String> mImagesBanner;
    private Utils mUtils;

    public CustomImagesPagerAdapter(Context context, ArrayList<Integer> mImages) {
        this.mContext = context;
        this.mImages = mImages;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mUtils = new Utils();
    }

    public CustomImagesPagerAdapter(Context context, ArrayList<String> mImages, String Dummy) {
        this.mContext = context;
        this.mImagesBanner = mImages;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mUtils = new Utils();
    }

    @Override
    public int getCount() {
       if (mImages != null && mImagesBanner == null) {
            return mImages.size();
        } else {
            return mImagesBanner.size();
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CustomImagesBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.custom_images, container, false);

        try {
            if (mImages != null && mImagesBanner == null) {
                binding.itemScrollImage.setImageResource(mImages.get(position));
            } else {
                mUtils.loadImageInImageview(mContext, mImagesBanner.get(position), binding.itemScrollImage);
            }
            container.addView(binding.getRoot(), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

}
