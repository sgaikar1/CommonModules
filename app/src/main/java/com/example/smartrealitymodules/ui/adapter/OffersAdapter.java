package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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
    public GetOffersRes.Result result;
    private List<GetOffersRes.Result> data;

    public OffersAdapter(Activity activity, List<GetOffersRes.Result> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.activity = activity;
        mUtils = new Utils();
        mDateUtils = new DateUtils();
    }


    @Override
    public OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OffersRowBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.offers_row, parent, false);
        return new OffersViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final OffersViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data.get(position), v);
            }
        });

        holder.binding.setOffers(data.get(position));
        holder.binding.setDateUtils(mDateUtils);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(GetOffersRes.Result Item, View v);
    }

//    public class OffersViewHolder extends RecyclerView.ViewHolder {
//        private final FrameLayout floffer;
//        private final TextView textitemrecyclerofferdescription;
//        private final TextView textitemrecyclerofferdate;
//        private final TextView textitemrecycleroffertitle;
//        private final ImageView imageitemrecycleroffershare;
//        private final ImageView imageitemrecyclerofferimage;
//
//        public OffersViewHolder(View itemView) {
//            super(itemView);
//            floffer = (FrameLayout) itemView.findViewById(R.id.fl_offer);
//            textitemrecyclerofferdescription = (TextView) itemView.findViewById(R.id.text_item_recycler_offer_description);
//            textitemrecyclerofferdate = (TextView) itemView.findViewById(R.id.text_item_recycler_offer_date);
//            textitemrecycleroffertitle = (TextView) itemView.findViewById(R.id.text_item_recycler_offer_title);
//            imageitemrecycleroffershare = (ImageView) itemView.findViewById(R.id.image_item_recycler_offer_share);
//            imageitemrecyclerofferimage = (ImageView) itemView.findViewById(R.id.image_item_recycler_offer_image);
//        }
//    }

    public class OffersViewHolder extends RecyclerView.ViewHolder {

        OffersRowBinding binding;

        public OffersViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }
    }

}
