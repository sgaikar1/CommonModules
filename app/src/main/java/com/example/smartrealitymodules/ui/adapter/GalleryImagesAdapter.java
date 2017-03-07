package com.example.smartrealitymodules.ui.adapter;

/**
 * Created by user on 28/2/17.
 */

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ItemRecyclerGalleryImagesBinding;
import com.example.smartrealitymodules.ui.activity.ImagePagerActivity;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Dhaval Parmar on 9/11/2016.
 */
public class GalleryImagesAdapter extends RecyclerView.Adapter<GalleryImagesAdapter.GalleryImagesViewHolder> {

    private final LayoutInflater mLayoutInflater;
    ArrayList<String> data;
    Context mContext;
    Utils mUtils;
    private ItemRecyclerGalleryImagesBinding binding;

    public GalleryImagesAdapter(ArrayList<String> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        mUtils = new Utils();
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public GalleryImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_recycler_gallery_images, parent, false);

        GalleryImagesViewHolder mHolder = new GalleryImagesViewHolder(binding.getRoot());
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final GalleryImagesViewHolder holder, final int position) {
        mUtils.loadImageInImageview(mContext, data.get(position), holder.binding.imageItemRecyclerGalleryImages);

        holder.binding.llGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent album = new Intent(mContext, ImagePagerActivity.class);
                album.putStringArrayListExtra(Constants.IMAGELIST_KEY, data);
                album.putExtra(Constants.POSITION_KEY, position);
                mContext.startActivity(album);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GalleryImagesViewHolder extends RecyclerView.ViewHolder {

        ItemRecyclerGalleryImagesBinding binding;
        public GalleryImagesViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}