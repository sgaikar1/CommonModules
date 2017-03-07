package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ItemRecyclerLayoutImagesBinding;
import com.example.smartrealitymodules.models.response.GetLayoutDetailsRes;
import com.example.smartrealitymodules.utils.Utils;

/**
 * Created by user on 2/3/17.
 */
public class LayoutImagesAdapter extends RecyclerView.Adapter<LayoutImagesAdapter.LayoutImagesViewHolder> {
    private final OnItemClickListener listener;
    private final LayoutInflater mLayoutInflater;
    GetLayoutDetailsRes.Result data;
    Activity mContext;
    Utils mUtils;

    public LayoutImagesAdapter(Activity mContext, GetLayoutDetailsRes.Result data, OnItemClickListener listener) {
        this.data = data;
        this.mContext = mContext;
        this.listener = listener;
        mUtils = new Utils();
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public LayoutImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecyclerLayoutImagesBinding binding = DataBindingUtil.inflate(mLayoutInflater,R.layout.item_recycler_layout_images,parent,false);
        LayoutImagesViewHolder mHolder = new LayoutImagesViewHolder(binding.getRoot());
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final LayoutImagesViewHolder holder, final int position) {

        holder.binding.setUtils(mUtils);
        holder.binding.setMasterLayouts(data.getMasterLayout().get(position));

        /*mUtils.loadImageInImageview(mContext, data.getMasterLayout().get(position).getFilePath(), holder.binding.imageItemRecyclerLayoutImages);
        holder.binding.textItemRecyclerLayoutName.setText(data.getMasterLayout().get(position).getTagName());

        holder.binding.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(data, view, position);
            }
        });
        holder.binding.relativeItemRecyclerLayoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(data, view, position);
            }
        });*/
    }

    public interface OnItemClickListener {
        void onClick(GetLayoutDetailsRes.Result Item, View view, int position);
    }

    @Override
    public int getItemCount() {
//        return 1;
        return data.getMasterLayout().size();
    }

    public class LayoutImagesViewHolder extends RecyclerView.ViewHolder {

        ItemRecyclerLayoutImagesBinding binding;

        public LayoutImagesViewHolder(View itemView) {
            super(itemView);
            binding =DataBindingUtil.bind(itemView);
            }
    }
}
