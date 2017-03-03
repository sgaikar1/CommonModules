package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.models.eventbus.Interest;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.request.ProjectInterestedInReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.ProjectListingRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.ProjectListingPresenter;
import com.example.smartrealitymodules.mvp.view.ProjectListingView;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.ui.adapter.ProjectListingAdapter;
import com.example.smartrealitymodules.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by user on 27/2/17.
 */

public class ProjectListingActivity extends BaseActivity implements ProjectListingView {
    private static final String TAG = ProjectListingActivity.class.getSimpleName();
    @Inject
    public MainModel mainModel;
    private TextView txtprojectslisting;
    private RecyclerView recyclerprojectslisting;
    private ProgressBar progress;
    private SwipeRefreshLayout swipetorefresh;
    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            // TODO: 27/2/17 change filter icon when swipe refresh starts
//            filteritem.setIcon(R.drawable.ic_action_filter);

            if (cd.isConnectingToInternet()) {
                apiProjectListing();
            } else {
                mUtils.toastAlert(ProjectListingActivity.this, getString(R.string.no_internet));
            }
        }
    };
    private LinearLayoutManager mLayoutManager;
    private Context mContext;
    private ArrayList<ProjectListingRes.Result> listAll;
    private ProjectListingRes projectlist;
    private ImageView interestedimageview;
    private ProjectListingAdapter adapter;
    private Animation pulse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);

        renderView();
        init();
        apiProjectListing();
    }

    private void renderView() {
        setContentView(R.layout.activity_project_listing);
        swipetorefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);
        recyclerprojectslisting = (RecyclerView) findViewById(R.id.recycler_projects_listing);
        txtprojectslisting = (TextView) findViewById(R.id.txt_projects_listing);
        pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setProgressBar();
    }

    private void init() {
        swipetorefresh.setColorSchemeResources(R.color.colorAccent);
        swipetorefresh.setOnRefreshListener(mOnRefreshListener);

        recyclerprojectslisting.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerprojectslisting.setLayoutManager(mLayoutManager);
    }

    private void apiProjectListing() {
        if (cd.isConnectingToInternet()) {

            swipetorefresh.setRefreshing(true);

            CommonReq obj = new CommonReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID);
            ProjectListingPresenter presenter = new ProjectListingPresenter(mainModel, this);
            presenter.getProjectList(obj, ApiNames.ProjectListing);
        } else {
            mUtils.toastAlert(this, getString(R.string.no_internet));
            // TODO: 27/2/17 load from database if no internet connection
//            setOfflineData(dBhelper.getValuesFromModule(dBhelper.PROJECTLISTING));
        }
    }

    private void apiProjectInterestedIn(String projId, String value) {
        ProjectInterestedInReq obj = new ProjectInterestedInReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID, projId, value);
        ProjectListingPresenter presenter = new ProjectListingPresenter(mainModel, this);
        presenter.getProjectInterestedIn(mContext, obj, ApiNames.ProjectInterestedIn);
    }

    @Override
    public void getProjectListSuccess(final ProjectListingRes projectListingRes) {
        txtprojectslisting.setVisibility(View.GONE);
        recyclerprojectslisting.setVisibility(View.VISIBLE);
        listAll = projectListingRes.getResult();
        projectlist = projectListingRes;
        adapter = new ProjectListingAdapter(ProjectListingActivity.this, projectListingRes.getResult(),
                new ProjectListingAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(ProjectListingRes.Result item, View view, int position) {
                        switch (view.getId()) {
                            case R.id.image_item_recycler_project_interested:
                                interestedimageview = (ImageView) view;
                                if (cd.isConnectingToInternet()) {
                                    if (listAll.get(position).getInterestedIn().equalsIgnoreCase("false")) {

                                        interestedimageview.startAnimation(pulse);
                                        interestedimageview.setImageResource(R.drawable.ic_heart_red);
//                        data.get(position).setInterestedIn("True");
                                        EventBus.getDefault().post(new Interest("True", listAll.get(position).getProjectID(), "", position));

                                    } else if (listAll.get(position).getInterestedIn().equalsIgnoreCase("true")) {

                                        interestedimageview.startAnimation(pulse);
                                        interestedimageview.setImageResource(R.drawable.ic_heart_white);
//                        data.get(position).setInterestedIn("False");
                                        EventBus.getDefault().post(new Interest("False", listAll.get(position).getProjectID(), "", position));
                                    }
                                } else {
                                    mUtils.toastAlert(ProjectListingActivity.this, mContext.getString(R.string.no_internet));
                                }
                                break;

                            case R.id.image_item_recycler_project_call:
                                mUtils.openCallScreen(mContext, projectListingRes.getResult().get(position).getContactNo());
                                break;

                            case R.id.image_item_recycler_project_location:
                                String uri = "http://maps.google.com/maps?q=loc:" + projectListingRes.getResult().get(position).getLat() + "," + projectListingRes.getResult().get(position).getLong() + " (Current Project)";
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                startActivity(intent);
                                break;
                            default:
                                Intent in = new Intent(mContext, ProjectDetailsActivity.class);
                                in.putExtra(Constants.PROJECTNAME_KEY, item.getProjectName());
                                in.putExtra(Constants.PROJECTID_KEY, item.getProjectID());
                                in.putExtra(Constants.PROJECTIMAGE_KEY, item.getFilePath());
                                startActivity(in);
                                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                                break;
                        }
                    }
                });

        recyclerprojectslisting.setAdapter(adapter);
    }

    @Override
    public void showEmptyView() {
        txtprojectslisting.setVisibility(View.VISIBLE);
        recyclerprojectslisting.setVisibility(View.GONE);

    }

    @Override
    public void removeRefresh() {
        swipetorefresh.setRefreshing(false);

    }

    @Override
    public void getProjectInterestedIn(CommonRes commonRes) {

    }

    @Override
    public void resetInterestedImage() {
        for (int x = 0; x < projectlist.getResult().size(); x++) {
            if (projectlist.getResult().get(x).getInterestedIn().equalsIgnoreCase("false")) {
                interestedimageview
                        .setImageResource(R.drawable.ic_heart_red);
                projectlist.getResult().get(x).setInterestedIn("True");
            } else {
                interestedimageview
                        .setImageResource(R.drawable.ic_heart_white);
                projectlist.getResult().get(x).setInterestedIn("False");
            }
        }
    }

    @Subscribe
    public void onMessageEvent(Interest event) {
        try {
            if (event.getValue() != null && event.getId() != null) {
                mUtils.logMe("eventbus", "Interest");

//                to insert updated interest value in db

                for (int i = 0; i < listAll.size(); i++) {
                    if (listAll.get(i).getProjectID().equalsIgnoreCase(event.getId())) {
                        listAll.get(i).setInterestedIn(event.getValue());
                    }
                }
                projectlist.setResult(listAll);

//                dBhelper.insertintoJSONTable(dBhelper.PROJECTLISTING, new Gson().toJson(projectlist), "");

                if (event.getFrom().length() == 0) {

                    if (event.getValue().equalsIgnoreCase("true")) {
                        mUtils.toastInfo(this, "Thank you for showing Interest");
                    }

                    apiProjectInterestedIn(event.getId(), event.getValue());
                } else {
                    adapter.notifyDataSetChanged();
                }
            } else {
                mUtils.toastAlert(this, getString(R.string.data_error));
            }
        } catch (Exception e) {
            mUtils.logMe("ex", e.toString());
        }
    }

}
