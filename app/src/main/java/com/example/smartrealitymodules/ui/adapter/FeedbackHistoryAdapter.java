package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.ItemRecyclerFeedbackHistoryBinding;
import com.example.smartrealitymodules.models.response.DisplayFeedbackRes;
import com.example.smartrealitymodules.utils.DateUtils;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;

/**
 * Created by user on 9/3/17.
 */
public class FeedbackHistoryAdapter extends RecyclerView.Adapter<FeedbackHistoryAdapter.FeedbackHistoryViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final OnItemClickListener listener;
    ArrayList<DisplayFeedbackRes.Result> data;
    Activity mContext;
    Utils mUtils;
    DateUtils mDateUtils;
    private ItemRecyclerFeedbackHistoryBinding binding;

    public FeedbackHistoryAdapter(Activity mContext, ArrayList<DisplayFeedbackRes.Result> data, OnItemClickListener listener) {
        this.data = data;
        this.mContext = mContext;
        this.listener = listener;
        mUtils = new Utils();
        mDateUtils = new DateUtils();
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public FeedbackHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(mLayoutInflater,R.layout.item_recycler_feedback_history,parent,false);
        FeedbackHistoryViewHolder mHolder = new FeedbackHistoryViewHolder(binding.getRoot());
        return mHolder;
    }

    @Override
    public void onBindViewHolder(FeedbackHistoryViewHolder holder, final int position) {

        binding.setResult(data.get(position));

        final String date[] = data.get(position).getDate().split(" ");
        holder.binding.textItemRecyclerFeedbackDate.setText(mDateUtils.changeDateFormat(date[0],
                mDateUtils.dd__MM__yyyy, mDateUtils.dd_MMM_yyyy)+"\n"+date[1]+" "+date[2]);


        holder.binding.imageItemRecyclerFeedbackDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(data.get(position),view,position);


            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(data.get(position),view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FeedbackHistoryViewHolder extends RecyclerView.ViewHolder {

        ItemRecyclerFeedbackHistoryBinding binding;
        public FeedbackHistoryViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public interface OnItemClickListener {
        void onClick(DisplayFeedbackRes.Result Item, View v, int position);
    }


}
