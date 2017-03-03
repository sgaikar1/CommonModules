package com.example.smartrealitymodules.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;

/**
 * Created by user on 2/3/17.
 */
public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.AmenitiesViewHolder> {

    ArrayList<ProjectDetailsRes.Amenity> data;
    Context mContext;
    Utils mUtils;

    private int size;
    private int imageSize;
    private int padding;

    public AmenitiesAdapter(Context mContext, ArrayList<ProjectDetailsRes.Amenity> data) {
        this.data = data;
        this.mContext = mContext;
        mUtils = new Utils();
    }

    @Override
    public AmenitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_amenities, parent, false);
        AmenitiesViewHolder mHolder = new AmenitiesViewHolder(mView);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final AmenitiesViewHolder holder, final int position) {

        holder.itemView.getLayoutParams().width = size;
        holder.itemView.getLayoutParams().height = size + 10;

        holder.textItemRecyclerAmenitiesName.setText(data.get(position).getTitle());
        mUtils.loadImageInImageview(mContext, data.get(position).getIconPath(), holder.imageItemRecyclerAmenitiesImages);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setImageSize(int size) {
        this.size = size;
        imageSize = size - padding * 2;
    }

    public class AmenitiesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageItemRecyclerAmenitiesImages;
        private TextView textItemRecyclerAmenitiesName;

        public AmenitiesViewHolder(View itemView) {
            super(itemView);
            imageItemRecyclerAmenitiesImages = (ImageView) itemView.findViewById(R.id.image_item_recycler_amenities_images);
            textItemRecyclerAmenitiesName = (TextView) itemView.findViewById(R.id.text_item_recycler_amenities_name);
        }
    }
}