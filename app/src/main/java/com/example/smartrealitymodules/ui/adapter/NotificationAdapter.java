package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.NotificationsRowBinding;
import com.example.smartrealitymodules.models.response.GetAllJumbleNotificationsRes;
import com.example.smartrealitymodules.utils.DateUtils;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private final OnItemClickListener listener;
    private final DateUtils mDateUtils;
    private final Activity activity;
    private List<GetAllJumbleNotificationsRes.Result> data;

    public NotificationAdapter(Activity activity, List<GetAllJumbleNotificationsRes.Result> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.activity = activity;
        mDateUtils = new DateUtils();
    }


    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NotificationsRowBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.notifications_row, parent, false);
        return new NotificationViewHolder(binding.getRoot());
    }


    @Override
    public void onBindViewHolder(final NotificationViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(position), v);
            }
        });
        holder.binding.setNotification(data.get(position));
        holder.binding.setDateUtils(mDateUtils);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onClick(GetAllJumbleNotificationsRes.Result Item, View v);
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        NotificationsRowBinding binding;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }


    }


}
