package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.response.ProjectListingRes;
import com.example.smartrealitymodules.utils.Utils;

import java.util.List;

public class ProjectListingAdapter extends RecyclerView.Adapter<ProjectListingAdapter.ProjectListingViewHolder> {
    private final OnItemClickListener listener;
    private final Utils mUtils;
    private final Activity activity;
    public ProjectListingRes.Result result;
    private List<ProjectListingRes.Result> data;

    public ProjectListingAdapter(Activity activity, List<ProjectListingRes.Result> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.activity = activity;
        mUtils = new Utils();
    }


    @Override
    public ProjectListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_projects_list, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ProjectListingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ProjectListingViewHolder holder, final int position) {
        holder.textitemrecyclerprojectname.setText(data.get(position).getProjectName());

        if (data.get(position).getMinBudget().equals("0")) {
            holder.textitemrecyclerprojectprice.setVisibility(View.GONE);
        } else {
            holder.textitemrecyclerprojectprice.setVisibility(View.VISIBLE);
            holder.textitemrecyclerprojectprice.setText("₹ " + data.get(position).getMinBudget());
        }
        holder.textitemrecyclerprojectflattype.setText(data.get(position).getApartmentType());

        mUtils.loadImageInImageview(activity, data.get(position).getFilePath(), holder.imageitemrecyclerprojectimage);

        if (data.get(position).getInterestedIn().equalsIgnoreCase("true")) {
            holder.imageitemrecyclerprojectinterested.setImageResource(R.drawable.ic_heart_red);
        } else {
            holder.imageitemrecyclerprojectinterested.setImageResource(R.drawable.ic_heart_white);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(position), v, position);
            }
        });

        holder.imageitemrecyclerprojectinterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(position), v, position);
            }
        });

        holder.imageitemrecyclerprojectlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(position), v, position);
            }
        });

        holder.imageitemrecyclerprojectcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(position), v, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onClick(ProjectListingRes.Result Item, View view, int position);
    }

    public class ProjectListingViewHolder extends RecyclerView.ViewHolder {

        private final FrameLayout frameitemrecyclerproject;
        private final ImageView imageitemrecyclerprojectcall, imageitemrecyclerprojectlocation, imageitemrecyclerprojectinterested, imageitemrecyclerprojectimage;
        private final TextView textitemrecyclerprojectflattype, textitemrecyclerprojectprice, textitemrecyclerprojectname;

        public ProjectListingViewHolder(View view) {
            super(view);
            frameitemrecyclerproject = (FrameLayout) view.findViewById(R.id.frame_item_recycler_project);
            imageitemrecyclerprojectcall = (ImageView) view.findViewById(R.id.image_item_recycler_project_call);
            imageitemrecyclerprojectlocation = (ImageView) view.findViewById(R.id.image_item_recycler_project_location);
            imageitemrecyclerprojectinterested = (ImageView) view.findViewById(R.id.image_item_recycler_project_interested);
            textitemrecyclerprojectflattype = (TextView) view.findViewById(R.id.text_item_recycler_project_flat_type);
            textitemrecyclerprojectprice = (TextView) view.findViewById(R.id.text_item_recycler_project_price);
            textitemrecyclerprojectname = (TextView) view.findViewById(R.id.text_item_recycler_project_name);
            imageitemrecyclerprojectimage = (ImageView) view.findViewById(R.id.image_item_recycler_project_image);


        }


    }


}
