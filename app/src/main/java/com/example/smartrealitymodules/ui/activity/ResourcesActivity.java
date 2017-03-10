package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityResourcesBinding;
import com.example.smartrealitymodules.models.request.GetResourceRequest;
import com.example.smartrealitymodules.models.response.GetResourcesResponse;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.ResourcesPresenter;
import com.example.smartrealitymodules.mvp.view.ResourcesView;
import com.example.smartrealitymodules.ui.adapter.ExpandableResourcesAdapter;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.Utils;

import javax.inject.Inject;

/**
 * Created by user on 10/3/17.
 */

public class ResourcesActivity extends BaseActivity implements ResourcesView {
    @Inject
    public MainModel mainModel;
    private ActivityResourcesBinding binding;
    private ResourcesPresenter presenter;
    private Context mContext;
    private GetResourcesResponse resourcesData;
    private ExpandableResourcesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        mContext = this;

        renderView();
        init();
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_resources);
        setProgressBar();
    }

    private void init() {

        presenter = new ResourcesPresenter(mainModel, this);
        if (isConnected) {
            ApiGetResources();
        } else {
            showToast(getResources().getString(R.string.no_internet));
        }
    }

    private void ApiGetResources() {
        // TODO: 10/3/17 not tested: Reason-base url different
        GetResourceRequest obj = new GetResourceRequest(ApiNames.GET_RESOURCES, Constants.USERID);
        presenter.apiGetResources(mContext, obj, ApiNames.GET_RESOURCES);

    }

    @Override
    public void getResourcesSuccess(GetResourcesResponse getResourcesResponse) {
        binding.tvNoData.setVisibility(View.GONE);
        binding.explvEmergencyContacts.setVisibility(View.VISIBLE);
        resourcesData = getResourcesResponse;
        adapter = new ExpandableResourcesAdapter(this, getResourcesResponse.getData(), new ExpandableResourcesAdapter.OnItemClickListener() {

            @Override
            public void onClick(String number) {
                if (number.equalsIgnoreCase("")) {
                    new Utils().openCallScreen(mContext,number);
                } else {
                   showToast(getResources().getString(R.string.no_mobile_number));
                }
            }
        });

        binding.explvEmergencyContacts.setAdapter(adapter);
    }

    @Override
    public void showEmptyView(GetResourcesResponse getResourcesResponse) {
        binding.tvNoData.setVisibility(View.VISIBLE);
        binding.tvNoData.setText(getResourcesResponse.getMessage());
        binding.explvEmergencyContacts.setVisibility(View.GONE);
    }
}
