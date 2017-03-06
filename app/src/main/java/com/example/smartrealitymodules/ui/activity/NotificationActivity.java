package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityNotificationBinding;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.response.GetAllJumbleNotificationsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.NotificationPresenter;
import com.example.smartrealitymodules.mvp.view.NotificationView;
import com.example.smartrealitymodules.ui.adapter.NotificationAdapter;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.Utils;

import javax.inject.Inject;

/**
 * Created by user on 15/2/17.
 */

public class NotificationActivity extends BaseActivity implements NotificationView {
    @Inject
    public MainModel mainModel;
    private RecyclerView recyclernotification;
    private TextView txtnotificationslisting;
    private LinearLayoutManager mLayoutManager;
    private Utils mUtils;
    private Context mContext;
    private NotificationPresenter presenter;
    private ActivityNotificationBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);

        renderView();
        init();
    }

    private void init() {
        mUtils = new Utils();
        apiGetAllJumbleNotifications();
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_notification);
        setProgressBar();
        binding.recyclerNotification.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        binding.recyclerNotification.setLayoutManager(mLayoutManager);
    }

    private void apiGetAllJumbleNotifications() {
        if (isConnected) {

            CommonReq obj = new CommonReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID);
            presenter = new NotificationPresenter(mainModel, this);
            presenter.getNotificationList(obj, ApiNames.GetAllJumbleNotifications);
        } else {
            removeWait();
            mUtils.toastAlert(this, getString(R.string.no_internet));
            // TODO: 20/2/17 If internet not available display data from local database
//            setOfflineData(dBhelper.getValuesFromModule(dBhelper.NOTIFICATIONS));
        }
    }

    @Override
    public void getNotificationListSuccess(GetAllJumbleNotificationsRes response) {

        NotificationAdapter adapter = new NotificationAdapter(NotificationActivity.this, response.getResult(),
                new NotificationAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(GetAllJumbleNotificationsRes.Result Item, View v) {
                        showToast(Item.getTitle());
                    }
                });

        binding.recyclerNotification.setAdapter(adapter);
    }

    @Override
    public void showEmptyView() {
        progressBar.setVisibility(View.GONE);
        binding.txtNotificationsListing.setVisibility(View.VISIBLE);
        binding.recyclerNotification.setVisibility(View.GONE);
    }

}
