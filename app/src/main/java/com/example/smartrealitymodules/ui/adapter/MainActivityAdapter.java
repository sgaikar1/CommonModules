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
import com.example.smartrealitymodules.ui.activity.ComplaintsHistoryActivity;
import com.example.smartrealitymodules.ui.activity.ContactUsActivity;
import com.example.smartrealitymodules.ui.activity.FeedbackHistoryActivity;
import com.example.smartrealitymodules.ui.activity.LoginTypeActivity;
import com.example.smartrealitymodules.ui.activity.NotificationActivity;
import com.example.smartrealitymodules.ui.activity.OffersActivity;
import com.example.smartrealitymodules.ui.activity.ProjectListingActivity;
import com.example.smartrealitymodules.ui.activity.ReferFriendActivity;
import com.example.smartrealitymodules.ui.activity.ResourcesActivity;
import com.example.smartrealitymodules.ui.activity.ShareActivity;
import com.example.smartrealitymodules.ui.activity.SplashFadeInActivity;
import com.example.smartrealitymodules.ui.activity.SplashVideoActivity;
import com.example.smartrealitymodules.ui.activity.UserProfileActivity;

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
                    case 6:
                        Intent intent7 = new Intent(mContext, ProjectListingActivity.class);
                        mContext.startActivity(intent7);
                        break;
                    case 7:
                        Intent intent8 = new Intent(mContext, UserProfileActivity.class);
                        mContext.startActivity(intent8);
                        break;
                    case 8:
                        Intent intent9 = new Intent(mContext, LoginTypeActivity.class);
                        mContext.startActivity(intent9);
                        break;
                    case 9:
                        Intent intent10 = new Intent(mContext, FeedbackHistoryActivity.class);
                        mContext.startActivity(intent10);
                        break;
                    case 10:
                        Intent intent11 = new Intent(mContext, ComplaintsHistoryActivity.class);
                        mContext.startActivity(intent11);
                        break;
                    case 11:
                        Intent intent12 = new Intent(mContext, SplashFadeInActivity.class);
                        mContext.startActivity(intent12);
                        break;
                    case 12:
                        Intent intent13 = new Intent(mContext, SplashVideoActivity.class);
                        mContext.startActivity(intent13);
                        break;
                    case 13:
                        Intent intent14 = new Intent(mContext, ResourcesActivity.class);
                        mContext.startActivity(intent14);
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