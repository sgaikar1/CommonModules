package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.response.GetAllJumbleNotificationsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.NotificationPresenter;
import com.example.smartrealitymodules.mvp.view.NotificationView;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.ui.adapter.NotificationAdapter;
import com.example.smartrealitymodules.utils.ConnectionDetector;
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
    private ConnectionDetector cd;
    private Context mContext;
    private NotificationPresenter presenter;

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
        cd = new ConnectionDetector(this);
        apiGetAllJumbleNotifications();
    }

    private void renderView() {
        setContentView(R.layout.activity_notification);
        setProgressBar();
        recyclernotification = (RecyclerView) findViewById(R.id.recycler_notification);
        txtnotificationslisting = (TextView) findViewById(R.id.txt_notifications_listing);
        recyclernotification.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclernotification.setLayoutManager(mLayoutManager);
    }

    private void apiGetAllJumbleNotifications() {
        if (cd.isConnectingToInternet()) {

            CommonReq obj = new CommonReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID);
            presenter = new NotificationPresenter(mainModel, this);
            presenter.getNotificationList(obj, ApiNames.GetAllJumbleNotifications);
        } else {
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
                    public void onClick(GetAllJumbleNotificationsRes.Result Item) {
                        mUtils.toastMe(mContext, Item.getTitle());
                    }
                });

        recyclernotification.setAdapter(adapter);
    }

    @Override
    public void showEmptyView() {
        progressBar.setVisibility(View.GONE);
        txtnotificationslisting.setVisibility(View.VISIBLE);
        recyclernotification.setVisibility(View.GONE);
    }

}
