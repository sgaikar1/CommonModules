package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityLayoutBinding;
import com.example.smartrealitymodules.models.request.GetLayoutDetailsReq;
import com.example.smartrealitymodules.models.response.GetLayoutDetailsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.LayoutPresenter;
import com.example.smartrealitymodules.mvp.view.LayoutView;
import com.example.smartrealitymodules.ui.adapter.LayoutImagesAdapter;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import javax.inject.Inject;

/**
 * Created by user on 2/3/17.
 */
public class LayoutActivity extends BaseActivity implements LayoutView {
    private Context mContext;
    @Inject
    public MainModel mainModel;
    private String projectId;
    private LinearLayoutManager mLayoutManager;
    private LayoutImagesAdapter mLayoutImagesAdapter;
    private ActivityLayoutBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);

        getIntentValues();
        renderView();
        apiGetLayoutDetails();
    }

    public void getIntentValues() {
        Intent i = getIntent();
        projectId = i.getStringExtra(Constants.PROJECTID_KEY);
    }

    private void renderView() {

        binding = DataBindingUtil.setContentView(this,R.layout.activity_layout);

        setProgressBar();

        binding.recyclerLayoutListing.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        binding.recyclerLayoutListing.setLayoutManager(mLayoutManager);
    }


    private void apiGetLayoutDetails() {
        if (isConnected) {

            GetLayoutDetailsReq obj = new GetLayoutDetailsReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID, projectId);
            LayoutPresenter presenter = new LayoutPresenter(mainModel, this);
            presenter.GetLayoutDetails(mContext, obj, ApiNames.GetLayoutDetails);
        } else {
            mUtils.toastAlert(this, getString(R.string.no_internet));
            // TODO: 27/2/17 load from database if no internet connection
//            setOfflineData(dBhelper.getValuesFromProjectLayout(projectId));
        }
    }

    @Override
    public void getLayoutDetailsSuccess(GetLayoutDetailsRes getLayoutDetailsRes) {
        binding.txtLayoutListing.setVisibility(View.GONE);
        binding.recyclerLayoutListing.setVisibility(View.VISIBLE);
        mLayoutImagesAdapter = new LayoutImagesAdapter(this, getLayoutDetailsRes.getResult(), new LayoutImagesAdapter.OnItemClickListener() {
            @Override
            public void onClick(GetLayoutDetailsRes.Result data, View view, int position) {

                switch (view.getId()) {
                    case R.id.frame_layout:

                        // TODO: 2/3/17 This classes fetches data from database.. so implement database connectivity and then dont forget to implement below code of both case
                        /*if (data.getBlockLayout().get(position).getFilePath() != null && data.getBlockLayout().get(position).getFilePath().length() > 0) {
                            Intent album = new Intent(mContext, BlockLayoutActivity.class);
                            album.putParcelableArrayListExtra("BlockList", data.getBlockLayout());
                            startActivity(album);
                            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        } else {
                            Intent album = new Intent(mContext, FlatLayoutActivity.class);
                            album.putParcelableArrayListExtra("FlatList", data.getBlockLayout().get(position).getFlatLayout());
                            startActivity(album);
                            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        }*/
                        break;

                    case R.id.relative_item_recycler_layout_image_view:
                       /* Intent viewimage = new Intent(mContext, ImageActivity.class);
                        viewimage.putExtra("url", data.getMasterLayout().get(position).getFilePath());
                        mContext.startActivity(viewimage);*/
                        break;
                }
            }
        });
        binding.recyclerLayoutListing.setAdapter(mLayoutImagesAdapter);
    }

    @Override
    public void showEmptyView() {
        binding.txtLayoutListing.setVisibility(View.VISIBLE);
        binding.recyclerLayoutListing.setVisibility(View.GONE);
    }
}
