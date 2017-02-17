package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.BR;
import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.OffersRowBinding;
import com.example.smartrealitymodules.models.response.GetOffersRes;
import com.example.smartrealitymodules.utils.DateUtils;
import com.example.smartrealitymodules.utils.Utils;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {
    private final OnItemClickListener listener;
    private final Utils mUtils;
    private final DateUtils mDateUtils;
    private final Activity activity;
    private List<GetOffersRes.Result> data;
    public GetOffersRes.Result result;
    private Dialog alert;
    private OnItemClickListener itemClickListener;

    public OffersAdapter(Activity activity, List<GetOffersRes.Result> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.activity = activity;
        mUtils = new Utils();
        mDateUtils = new DateUtils();
    }


    @Override
    public OffersAdapter.OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        OffersRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.offers_row, parent, false);
        return new OffersViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(final OffersAdapter.OffersViewHolder holder, final int position) {

        OffersRowBinding offersRowBinding = holder.getBinding();

        this.result = data.get(position);
        offersRowBinding.setVariable(BR.offersAdapter, this);
//        holder.binding.setOffersAdapter(this);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class OffersViewHolder extends RecyclerView.ViewHolder {
        OffersRowBinding binding;
        public OffersViewHolder(OffersRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.executePendingBindings();

        }
        public OffersRowBinding getBinding() {
            return binding;
        }

    }

    public void showOnItemClick(View view, GetOffersRes.Result result) {
        itemClickListener.onClick(result);
    }

    public void registerItemClickListener(OnItemClickListener itemClickListsener) {
        this.itemClickListener = itemClickListsener;
    }



    public interface OnItemClickListener {
        void onClick(GetOffersRes.Result Item);
    }



}
