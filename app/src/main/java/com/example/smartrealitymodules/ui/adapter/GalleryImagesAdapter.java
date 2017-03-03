package com.example.smartrealitymodules.ui.adapter;

/**
 * Created by user on 28/2/17.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.ui.activity.ImagePagerActivity;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Dhaval Parmar on 9/11/2016.
 */
public class GalleryImagesAdapter extends RecyclerView.Adapter<GalleryImagesAdapter.GalleryImagesViewHolder> {

    ArrayList<String> data;
    Context mContext;
    Utils mUtils;

    public GalleryImagesAdapter(ArrayList<String> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        mUtils = new Utils();
    }

    @Override
    public GalleryImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_gallery_images, parent, false);
        GalleryImagesViewHolder mHolder = new GalleryImagesViewHolder(mView);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final GalleryImagesViewHolder holder, final int position) {
        mUtils.loadImageInImageview(mContext, data.get(position), holder.imageItemRecyclerGalleryImages);

        holder.ll_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent album = new Intent(mContext, ImagePagerActivity.class);
                album.putStringArrayListExtra("ImagesList", data);
                album.putExtra("position", position);
                mContext.startActivity(album);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GalleryImagesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageItemRecyclerGalleryImages;
        private LinearLayout ll_gallery;

        public GalleryImagesViewHolder(View itemView) {
            super(itemView);
            ll_gallery = (LinearLayout) itemView.findViewById(R.id.ll_gallery);
            imageItemRecyclerGalleryImages = (ImageView) itemView.findViewById(R.id.image_item_recycler_gallery_images);
        }
    }
}