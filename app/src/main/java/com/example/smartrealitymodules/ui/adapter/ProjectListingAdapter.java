package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ItemRecyclerProjectsListBinding;
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
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_projects_list, null);
//        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        ItemRecyclerProjectsListBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.item_recycler_projects_list, parent, false);
        return new ProjectListingViewHolder(binding.getRoot());
    }


    @Override
    public void onBindViewHolder(final ProjectListingViewHolder holder, final int position) {
        holder.binding.setProjects(data.get(position));
        holder.binding.setUtils(mUtils);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(position), v, position);
            }
        });

        holder.binding.imageItemRecyclerProjectInterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(position), v, position);
            }
        });

        holder.binding.imageItemRecyclerProjectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(position), v, position);
            }
        });

        holder.binding.imageItemRecyclerProjectCall.setOnClickListener(new View.OnClickListener() {
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

        private final ItemRecyclerProjectsListBinding binding;

        /* private final FrameLayout frameitemrecyclerproject;
                private final ImageView imageitemrecyclerprojectcall, imageitemrecyclerprojectlocation, imageitemrecyclerprojectinterested, imageitemrecyclerprojectimage;
                private final TextView textitemrecyclerprojectflattype, textitemrecyclerprojectprice, textitemrecyclerprojectname;
        */
        public ProjectListingViewHolder(View view) {
            super(view);
            /*frameitemrecyclerproject = (FrameLayout) view.findViewById(R.id.frame_item_recycler_project);
            imageitemrecyclerprojectcall = (ImageView) view.findViewById(R.id.image_item_recycler_project_call);
            imageitemrecyclerprojectlocation = (ImageView) view.findViewById(R.id.image_item_recycler_project_location);
            imageitemrecyclerprojectinterested = (ImageView) view.findViewById(R.id.image_item_recycler_project_interested);
            textitemrecyclerprojectflattype = (TextView) view.findViewById(R.id.text_item_recycler_project_flat_type);
            textitemrecyclerprojectprice = (TextView) view.findViewById(R.id.text_item_recycler_project_price);
            textitemrecyclerprojectname = (TextView) view.findViewById(R.id.text_item_recycler_project_name);
            imageitemrecyclerprojectimage = (ImageView) view.findViewById(R.id.image_item_recycler_project_image);
*/
            binding = DataBindingUtil.bind(view);
        }


    }


}
