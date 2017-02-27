package com.example.smartrealitymodules.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.ui.activity.AboutUsActivity;
import com.example.smartrealitymodules.ui.activity.ContactUsActivity;
import com.example.smartrealitymodules.ui.activity.NotificationActivity;
import com.example.smartrealitymodules.ui.activity.OffersActivity;
import com.example.smartrealitymodules.ui.activity.ReferFriendActivity;
import com.example.smartrealitymodules.ui.activity.ShareActivity;

import java.util.List;

/**
 * Created by user on 13/2/17.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private final Context mContext;
    private List<String> items;
    private int itemLayout;

    public MainActivityAdapter(Context mContext, List<String> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.mContext = mContext;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, final int position) {
        String item = items.get(position);
        holder.text.setText(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(mContext, ReferFriendActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(mContext, OffersActivity.class);
                        mContext.startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(mContext, NotificationActivity.class);
                        mContext.startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(mContext, ContactUsActivity.class);
                        mContext.startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(mContext, AboutUsActivity.class);
                        mContext.startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent(mContext, ShareActivity.class);
                        mContext.startActivity(intent6);
                        break;
                }

            }
        });
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}