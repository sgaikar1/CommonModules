package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.response.GetAllJumbleNotificationsRes;
import com.example.smartrealitymodules.utils.DateUtils;
import com.example.smartrealitymodules.utils.Utils;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private final OnItemClickListener listener;
    private final Utils mUtils;
    private final DateUtils mDateUtils;
    private final Activity activity;
    private List<GetAllJumbleNotificationsRes.Result> data;
    private Dialog alert;
    private OnItemClickListener itemClickListener;

    public NotificationAdapter(Activity activity, List<GetAllJumbleNotificationsRes.Result> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.activity = activity;
        mUtils = new Utils();
        mDateUtils = new DateUtils();
    }


    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_row, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new NotificationViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final NotificationViewHolder holder, final int position) {

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void showOnItemClick(View view, GetAllJumbleNotificationsRes.Result result) {
        itemClickListener.onClick(result);
    }

    public void registerItemClickListener(OnItemClickListener itemClickListsener) {
        this.itemClickListener = itemClickListsener;
    }

    public interface OnItemClickListener {
        void onClick(GetAllJumbleNotificationsRes.Result Item);
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout floffer;
        private final TextView textitemrecyclerofferdescription;
        private final TextView textitemrecyclerofferdate;
        private final TextView textitemrecycleroffertitle;
        private final ImageView imageitemrecycleroffershare;
        private final ImageView imageitemrecyclerofferimage;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            floffer = (FrameLayout) itemView.findViewById(R.id.fl_offer);
            textitemrecyclerofferdescription = (TextView) itemView.findViewById(R.id.text_item_recycler_offer_description);
            textitemrecyclerofferdate = (TextView) itemView.findViewById(R.id.text_item_recycler_offer_date);
            textitemrecycleroffertitle = (TextView) itemView.findViewById(R.id.text_item_recycler_offer_title);
            imageitemrecycleroffershare = (ImageView) itemView.findViewById(R.id.image_item_recycler_offer_share);
            imageitemrecyclerofferimage = (ImageView) itemView.findViewById(R.id.image_item_recycler_offer_image);


        }


    }


}
