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
import com.example.smartrealitymodules.databinding.ActivityComplaintsHistoryBinding;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.request.DeleteComplaintReq;
import com.example.smartrealitymodules.models.response.GetComplaintsHistoryRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.ComplaintsHistoryPresenter;
import com.example.smartrealitymodules.mvp.view.ComplaintsHistoryView;
import com.example.smartrealitymodules.ui.adapter.ComplaintsHistoryAdapter;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by user on 10/3/17.
 */

public class ComplaintsHistoryActivity extends BaseActivity implements ComplaintsHistoryView {
    private Context mContext;
    @Inject
    public MainModel mainModel;
    private ActivityComplaintsHistoryBinding binding;
    private LinearLayoutManager mLayoutManager;
    private ComplaintsHistoryPresenter presenter;
    private ArrayList<GetComplaintsHistoryRes.Facilitys> listAll;
    private ComplaintsHistoryAdapter mComplaintsHistoryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);
        renderView();
        init();
    }

    private void init() {
        presenter = new ComplaintsHistoryPresenter(mainModel, this);
        binding.recyclerComplaintHistory.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        binding.recyclerComplaintHistory.setLayoutManager(mLayoutManager);

        binding.floatAddComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplaintsHistoryActivity.this, ComplaintActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complaints_history);
        setProgressBar();
    }

    private void apiGetComplaintsRequest() {
        if (isConnected) {

            CommonReq obj = new CommonReq(Constants.PROJECTCODE, userType, userId);
            presenter.getComplaintsRequest(obj, ApiNames.GetComplaintRequest);

        } else {
            showToast(getString(R.string.no_internet));
//            setOfflineData(dBhelper.getValuesFromModule(dBhelper.COMPLAINTS));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiGetComplaintsRequest();
    }

    @Override
    public void getDisplayComplaints(GetComplaintsHistoryRes displayFeedbackRes) {
        binding.txtComplaintsListing.setVisibility(View.GONE);
        binding.recyclerComplaintHistory.setVisibility(View.VISIBLE);

        listAll = displayFeedbackRes.getFacility();

        mComplaintsHistoryAdapter = new ComplaintsHistoryAdapter(this, listAll, new ComplaintsHistoryAdapter.OnItemClickListener() {
            @Override
            public void onClick(GetComplaintsHistoryRes.Facilitys item, View v, int position) {
                switch (v.getId()) {
                    case R.id.image_item_recycler_feedback_delete:
                        deleteDialog(item.getFacilityID(), position);
                        break;
                    case R.id.ll_item_recycler_complaint:
                        Intent i = new Intent(mContext, ComplaintDetailsActivity.class);
                        i.putExtra("title", item.getTitle());
                        i.putExtra("status", item.getFacilityStatus());
                        i.putExtra("desc", item.getDescription());
                        i.putExtra("image", item.getImagePath());
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                }
            }
        }

        );
        binding.recyclerComplaintHistory.setAdapter(mComplaintsHistoryAdapter);
    }

    @Override
    public void showEmptyView() {
        binding.txtComplaintsListing.setVisibility(View.VISIBLE);
        binding.recyclerComplaintHistory.setVisibility(View.GONE);
    }

    private void deleteDialog(final String id, final int pos) {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setMessage("Do you want to Delete this complaint ?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        deleteComplaint(id, pos);
//                        EventBus.getDefault().post(new FacilityDelete(id, pos));
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

    private void deleteComplaint(String id, int pos) {
        listAll.remove(pos);
        apiDeleteComplaint(id);
        mComplaintsHistoryAdapter.notifyDataSetChanged();

        if(listAll.size()==0) {
            showEmptyView();
        }
    }
    private void apiDeleteComplaint(String id) {
        if (isConnected) {

            DeleteComplaintReq obj = new DeleteComplaintReq(Constants.PROJECTCODE, userType, userId, id);
            presenter.getDeleteComplaint(mContext ,obj,ApiNames.DeleteComplaintRequest);

        } else {
            showToast(getString(R.string.no_internet));
//            setOfflineData(dBhelper.getValuesFromModule(dBhelper.FEEDBACK));
        }
    }
}
