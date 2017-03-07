package com.example.smartrealitymodules.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ItemRecyclerAmenitiesBinding;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;

/**
 * Created by user on 2/3/17.
 */
public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.AmenitiesViewHolder> {

    private final LayoutInflater mLayoutInflater;
    ArrayList<ProjectDetailsRes.Amenity> data;
    Context mContext;
    Utils mUtils;

    private int size;
    private int imageSize;
    private int padding;

    public AmenitiesAdapter(Context mContext, ArrayList<ProjectDetailsRes.Amenity> data) {
        this.data = data;
        this.mContext = mContext;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mUtils = new Utils();
    }

    @Override
    public AmenitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecyclerAmenitiesBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_recycler_amenities, parent, false);

        AmenitiesViewHolder mHolder = new AmenitiesViewHolder(binding.getRoot());
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final AmenitiesViewHolder holder, final int position) {

        holder.itemView.getLayoutParams().width = size;
        holder.itemView.getLayoutParams().height = size + 10;
        holder.binding.setUtils(mUtils);
        holder.binding.setAmenity(data.get(position));
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


        private final ItemRecyclerAmenitiesBinding binding;

        public AmenitiesViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}