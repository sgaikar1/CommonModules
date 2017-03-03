package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.response.GetLayoutDetailsRes;
import com.example.smartrealitymodules.utils.Utils;

/**
 * Created by user on 2/3/17.
 */
public class LayoutImagesAdapter extends RecyclerView.Adapter<LayoutImagesAdapter.LayoutImagesViewHolder> {
    private final OnItemClickListener listener;
    GetLayoutDetailsRes.Result data;
    Activity mContext;
    Utils mUtils;

    public LayoutImagesAdapter(Activity mContext, GetLayoutDetailsRes.Result data, OnItemClickListener listener) {
        this.data = data;
        this.mContext = mContext;
        this.listener = listener;
        mUtils = new Utils();
    }

    @Override
    public LayoutImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_layout_images, parent, false);
        LayoutImagesViewHolder mHolder = new LayoutImagesViewHolder(mView);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final LayoutImagesViewHolder holder, final int position) {

        mUtils.loadImageInImageview(mContext, data.getMasterLayout().get(position).getFilePath(), holder.imageItemRecyclerLayoutImages);
        holder.textItemRecyclerLayoutName.setText(data.getMasterLayout().get(position).getTagName());

        holder.frame_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(data, view, position);
            }
        });
        holder.relativeItemRecyclerLayoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(data, view, position);
            }
        });
    }

    public interface OnItemClickListener {
        void onClick(GetLayoutDetailsRes.Result Item, View view, int position);
    }

    @Override
    public int getItemCount() {
        return 1;
//        return data.getMasterLayout().size();
    }

    public class LayoutImagesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageItemRecyclerLayoutImages;
        private TextView textItemRecyclerLayoutName;
        private FrameLayout frame_layout;
        private RelativeLayout relativeItemRecyclerLayoutImageView;

        public LayoutImagesViewHolder(View itemView) {
            super(itemView);
            frame_layout = (FrameLayout) itemView.findViewById(R.id.frame_layout);
            imageItemRecyclerLayoutImages = (ImageView) itemView.findViewById(R.id.image_item_recycler_layout_images);
            relativeItemRecyclerLayoutImageView = (RelativeLayout) itemView.findViewById(R.id.relative_item_recycler_layout_image_view);
            textItemRecyclerLayoutName = (TextView) itemView.findViewById(R.id.text_item_recycler_layout_name);
        }
    }
}
