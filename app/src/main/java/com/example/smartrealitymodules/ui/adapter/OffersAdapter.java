package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
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
    public OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_row, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new OffersViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final OffersViewHolder holder, final int position) {
        holder.textitemrecyclerofferdate.setText(mDateUtils.changeDateFormat(
                data.get(position).getEndDate(), mDateUtils.dd_MM_yyyy, mDateUtils.dd_MMM_yy));
        holder.textitemrecycleroffertitle.setText(data.get(position).getTitle());
        holder.textitemrecyclerofferdescription.setText((Html.fromHtml(data.get(position).getDescription())));
        mUtils.loadImageInImageview(activity, data.get(position).getOfferImage(), holder.imageitemrecyclerofferimage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(data.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
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

    public class OffersViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout floffer;
        private final TextView textitemrecyclerofferdescription;
        private final TextView textitemrecyclerofferdate;
        private final TextView textitemrecycleroffertitle;
        private final ImageView imageitemrecycleroffershare;
        private final ImageView imageitemrecyclerofferimage;

        public OffersViewHolder(View itemView) {
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
