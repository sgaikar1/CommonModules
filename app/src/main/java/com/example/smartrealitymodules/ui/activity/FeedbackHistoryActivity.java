package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityFeedBackHistoryBinding;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.request.DeleteFeedbackReq;
import com.example.smartrealitymodules.models.response.DisplayFeedbackRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.FeedbackHistoryPresenter;
import com.example.smartrealitymodules.mvp.view.FeedbackHistoryView;
import com.example.smartrealitymodules.ui.adapter.FeedbackHistoryAdapter;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.DateUtils;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by user on 9/3/17.
 */

public class FeedbackHistoryActivity extends BaseActivity implements FeedbackHistoryView {
    @Inject
    public MainModel mainModel;
    private ActivityFeedBackHistoryBinding binding;
    private LinearLayoutManager mLayoutManager;
    private FeedbackHistoryPresenter presenter;
    private FeedbackHistoryAdapter mFeedbackHistoryAdapter;
    private ArrayList<DisplayFeedbackRes.Result> listAll;
    private Context mContext;

    private ArrayList<String> idlist, typelist;
    private DateUtils mDateUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);
        renderView();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiDisplayFeedback();

    }

    private void init() {
        binding.recyclerFeedbackHistory.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        binding.recyclerFeedbackHistory.setLayoutManager(mLayoutManager);
        presenter = new FeedbackHistoryPresenter(mainModel,this);
        mDateUtils = new DateUtils();


        binding.floatAddHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FeedbackHistoryActivity.this, FeedBackActivity.class);
                i.putStringArrayListExtra("id", idlist);
                i.putStringArrayListExtra("type", typelist);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed_back_history);

        setProgressBar();

    }

    private void apiDisplayFeedback() {
        if (isConnected) {

            CommonReq obj = new CommonReq(Constants.PROJECTCODE, userType, userId);
            presenter.getDisplayFeedback(obj,ApiNames.DisplayFeedback);

        } else {
            showToast(getString(R.string.no_internet));
//            setOfflineData(dBhelper.getValuesFromModule(dBhelper.FEEDBACK));
        }
    }

    @Override
    public void getDisplayFeedBack(DisplayFeedbackRes displayFeedbackRes) {
        binding.txtFeedbackListing.setVisibility(View.GONE);
        binding.recyclerFeedbackHistory.setVisibility(View.VISIBLE);

        idlist = new ArrayList<>();
        typelist = new ArrayList<>();

        if (displayFeedbackRes.getFeedbackType() != null && !displayFeedbackRes.getFeedbackType().isEmpty()) {
            for(DisplayFeedbackRes.FeedbackTypes feedbackTypes : displayFeedbackRes.getFeedbackType()) {
                idlist.add(feedbackTypes.getTypeID());
                typelist.add(feedbackTypes.getF_Type());
            }
        }

        listAll = displayFeedbackRes.getResult();

        mFeedbackHistoryAdapter = new FeedbackHistoryAdapter(this, displayFeedbackRes.getResult(), new FeedbackHistoryAdapter.OnItemClickListener() {
            @Override
            public void onClick(DisplayFeedbackRes.Result item, View v, int position) {

                switch (v.getId()){
                    case R.id.image_item_recycler_feedback_delete:
                        deleteDialog(item.getFeedbackID(), position);
                        break;
                    default:

                        Intent i = new Intent(FeedbackHistoryActivity.this, FeedbackDetailsActivity.class);
                        String date[] = listAll.get(position).getDate().split(" ");
                        i.putExtra("title", listAll.get(position).getTitle());
                        i.putExtra("date", mDateUtils.changeDateFormat(date[0],
                                mDateUtils.dd__MM__yyyy, mDateUtils.dd_MMM_yyyy) + "\n" + date[1] + " " + date[2]);
                        i.putExtra("type", listAll.get(position).getType());
                        i.putExtra("desc", listAll.get(position).getDescription());
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;
                }
            }
        });
        binding.recyclerFeedbackHistory.setAdapter(mFeedbackHistoryAdapter);
    }

    @Override
    public void showEmptyView() {
        binding.txtFeedbackListing.setVisibility(View.VISIBLE);
        binding.recyclerFeedbackHistory.setVisibility(View.GONE);
    }

    private void deleteDialog(final String id, final int pos)
    {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("Do you want to Delete this feedback ?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                       deleteFeedback(id, pos);
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        dialog.show();

    }

    private void deleteFeedback(String id, int pos) {
        listAll.remove(pos);
        apiDeleteFeedback(id);
        mFeedbackHistoryAdapter.notifyDataSetChanged();

        if(listAll.size()==0) {
            showEmptyView();
        }
    }

    private void apiDeleteFeedback(String id) {
        if (isConnected) {

            DeleteFeedbackReq obj = new DeleteFeedbackReq(Constants.PROJECTCODE, userType, userId, id);
            presenter.getDeleteFeedback(mContext ,obj,ApiNames.DeleteFeedback);

        } else {
            showToast(getString(R.string.no_internet));
//            setOfflineData(dBhelper.getValuesFromModule(dBhelper.FEEDBACK));
        }
    }


}
