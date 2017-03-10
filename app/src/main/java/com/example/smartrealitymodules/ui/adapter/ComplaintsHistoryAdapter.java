package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ItemRecyclerComplaintHistoryBinding;
import com.example.smartrealitymodules.models.response.GetComplaintsHistoryRes;
import com.example.smartrealitymodules.utils.DateUtils;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;

/**
 * Created by user on 10/3/17.
 */
public class ComplaintsHistoryAdapter extends RecyclerView.Adapter<ComplaintsHistoryAdapter.ComplaintsHistoryViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final OnItemClickListener listener;
    ArrayList<GetComplaintsHistoryRes.Facilitys> data;
    Activity mContext;
    Utils mUtils;
    DateUtils mDateUtils;
    private ItemRecyclerComplaintHistoryBinding binding;

    public ComplaintsHistoryAdapter(Activity mContext, ArrayList<GetComplaintsHistoryRes.Facilitys> data, OnItemClickListener listener) {
        this.data = data;
        this.mContext = mContext;
        this.listener = listener;
        mUtils = new Utils();
        mDateUtils = new DateUtils();
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ComplaintsHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(mLayoutInflater,R.layout.item_recycler_complaint_history,parent,false);
        ComplaintsHistoryViewHolder mHolder = new ComplaintsHistoryViewHolder(binding.getRoot());
        return mHolder;
    }

    @Override
    public void onBindViewHolder(ComplaintsHistoryViewHolder holder, final int position) {

        if(data.get(position).getFacilityStatus().equalsIgnoreCase("Close")){
            holder.binding.textItemRecyclerComplaintStatus.setBackgroundResource(R.drawable.rounded_light_red);
        }else{
            holder.binding.textItemRecyclerComplaintStatus.setBackgroundResource(R.drawable.rounded_light_green);
        }

        holder.binding.imageItemRecyclerComplaintDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(data.get(position),view,position);
//                deleteDialog(data.get(position).getFacilityID(), position);

            }
        });

        holder.binding.llItemRecyclerComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.onClick(data.get(position),view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null?0 : data.size();
    }

    public class ComplaintsHistoryViewHolder extends RecyclerView.ViewHolder {

        ItemRecyclerComplaintHistoryBinding binding;
        public ComplaintsHistoryViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
           }
    }

    public interface OnItemClickListener {
        void onClick(GetComplaintsHistoryRes.Facilitys Item, View v, int position);
    }

}
