package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.models.eventbus.Interest;
import com.example.smartrealitymodules.models.request.ProjectDetailsReq;
import com.example.smartrealitymodules.models.request.ProjectInterestedInReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.ProjectDetailsPresenter;
import com.example.smartrealitymodules.mvp.view.ProjectDetailsView;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by user on 27/2/17.
 */
public class ProjectDetailsActivity extends BaseActivity implements View.OnClickListener, ProjectDetailsView {
    @Inject
    public MainModel mainModel;
    private Context mContext;
    private TextView txtprojectsdetail, textprojectdetailprojectprice, textprojectdetailprojecttype, textprojectdetailprojectlocation, textprojectdetailprojectname;
    private NestedScrollView nsvprojectdetail;
    private FrameLayout frameprojectdetaillayout, frameprojectdetailgallery;
    private ImageView imageprojectdetailslayout, imageprojectdetailsgallery, imageprojectdetail360view, imageprojectdetailwalkthrough, imageprojectdetailcall, imageprojectdetaillocation, imageprojectdetailinterested, imageprojectdetailproject;
    private LinearLayout linearprojectonlineblocking, linearprojectdetailschedulesitevisit, linearprojectdetailamenities, linearprojectdetailaboutproject, linearprice;
    private RelativeLayout relativestartfrom, viewloading;
    private String projectId;
    private String projectName;
    private String projectImage;
    private ProjectDetailsRes data;
    private Animation pulse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);

        getIntentValues();

        renderView();
        apiProjectDetails();
    }

    private void getIntentValues() {
        Intent i = getIntent();
        projectId = i.getStringExtra(Constants.PROJECTID_KEY);
        projectName = i.getStringExtra(Constants.PROJECTNAME_KEY);
        projectImage = i.getStringExtra(Constants.PROJECTIMAGE_KEY);
    }

    private void renderView() {
        setContentView(R.layout.activity_project_details);

        pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);

        txtprojectsdetail = (TextView) findViewById(R.id.txt_projects_detail);
        nsvprojectdetail = (NestedScrollView) findViewById(R.id.nsv_project_detail);
        frameprojectdetaillayout = (FrameLayout) findViewById(R.id.frame_project_detail_layout);
        imageprojectdetailslayout = (ImageView) findViewById(R.id.image_project_details_layout);
        frameprojectdetailgallery = (FrameLayout) findViewById(R.id.frame_project_detail_gallery);
        imageprojectdetailsgallery = (ImageView) findViewById(R.id.image_project_details_gallery);
        linearprojectonlineblocking = (LinearLayout) findViewById(R.id.linear_project_online_blocking);
        linearprojectdetailschedulesitevisit = (LinearLayout) findViewById(R.id.linear_project_detail_schedule_site_visit);
        linearprojectdetailamenities = (LinearLayout) findViewById(R.id.linear_project_detail_amenities);
        linearprojectdetailaboutproject = (LinearLayout) findViewById(R.id.linear_project_detail_about_project);
        relativestartfrom = (RelativeLayout) findViewById(R.id.relative_start_from);
        linearprice = (LinearLayout) findViewById(R.id.linear_price);
        textprojectdetailprojectprice = (TextView) findViewById(R.id.text_project_detail_project_price);
        textprojectdetailprojecttype = (TextView) findViewById(R.id.text_project_detail_project_type);
        textprojectdetailprojectlocation = (TextView) findViewById(R.id.text_project_detail_project_location);
        textprojectdetailprojectname = (TextView) findViewById(R.id.text_project_detail_project_name);
        imageprojectdetail360view = (ImageView) findViewById(R.id.image_project_detail_360_view);
        imageprojectdetailwalkthrough = (ImageView) findViewById(R.id.image_project_detail_walkthrough);
        imageprojectdetailcall = (ImageView) findViewById(R.id.image_project_detail_call);
        imageprojectdetaillocation = (ImageView) findViewById(R.id.image_project_detail_location);
        imageprojectdetailinterested = (ImageView) findViewById(R.id.image_project_detail_interested);
        imageprojectdetailproject = (ImageView) findViewById(R.id.image_project_detail_project);
        viewloading = (RelativeLayout) findViewById(R.id.view_loading);
        setProgressBar();

        frameprojectdetailgallery.setOnClickListener(this);
        frameprojectdetaillayout.setOnClickListener(this);
        linearprojectdetailamenities.setOnClickListener(this);
        linearprojectdetailschedulesitevisit.setOnClickListener(this);
        linearprojectdetailaboutproject.setOnClickListener(this);
        imageprojectdetailwalkthrough.setOnClickListener(this);
        imageprojectdetailcall.setOnClickListener(this);
        imageprojectdetaillocation.setOnClickListener(this);
        imageprojectdetailinterested.setOnClickListener(this);
        imageprojectdetail360view.setOnClickListener(this);
        linearprojectonlineblocking.setOnClickListener(this);
    }

    private void apiProjectDetails() {
        if (cd.isConnectingToInternet()) {

            ProjectDetailsReq obj = new ProjectDetailsReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID, projectId);
            ProjectDetailsPresenter presenter = new ProjectDetailsPresenter(mainModel, this);
            presenter.getProjectDetails(obj, ApiNames.ProjectListing);
        } else {
            mUtils.toastAlert(this, getString(R.string.no_internet));
            // TODO: 27/2/17 load from database if no internet connection
//            setOfflineData(dBhelper.getValuesFromProjectDetail(projectId));
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.frame_project_detail_gallery:
                Intent gallery = new Intent(ProjectDetailsActivity.this, GalleryActivity.class);
                gallery.putExtra(Constants.POSITION_KEY, 0);
                gallery.putExtra(Constants.WALKTHROUGH_URL_KEY, data.getResult().getWalkThroughURL());
                gallery.putParcelableArrayListExtra(Constants.ARCHITECTURE_KEY, data.getResult().getGallery().get(0).getArchitecture());
                gallery.putParcelableArrayListExtra(Constants.CUNSTRUCTION_KEY, data.getResult().getGallery().get(0).getConstruction());
                startActivity(gallery);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.frame_project_detail_layout:
                Intent layout = new Intent(ProjectDetailsActivity.this, LayoutActivity.class);
                layout.putExtra(Constants.PROJECTID_KEY, projectId);
                startActivity(layout);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.linear_project_detail_amenities:
                Intent amenities = new Intent(ProjectDetailsActivity.this, ProjectAmenitiesActivity.class);
                amenities.putParcelableArrayListExtra("AmenityImages", data.getResult().getAmenityImages());
                amenities.putParcelableArrayListExtra("Amenities", data.getResult().getAmenities());
                startActivity(amenities);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.linear_project_detail_schedule_site_visit:
                Intent schedule_visit = new Intent(ProjectDetailsActivity.this, ScheduleSiteVisitActivity.class);
                schedule_visit.putExtra(Constants.PROJECTID_KEY, projectId);
                schedule_visit.putExtra(Constants.PROJECTNAME_KEY, data.getResult().getProjectName());
                schedule_visit.putExtra(Constants.PROJECT_CITY_KEY, data.getResult().getCityName());
                startActivity(schedule_visit);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.linear_project_detail_about_project:
                Intent aboutProject = new Intent(ProjectDetailsActivity.this, AboutProjectActivity.class);
                aboutProject.putExtra(Constants.PROJECTNAME_KEY, data.getResult().getProjectName());
                aboutProject.putExtra(Constants.PROJECT_DESC_KEY, data.getResult().getAboutProject());
                aboutProject.putExtra(Constants.PROJECTIMAGE_KEY, data.getResult().getProjectImages());
                startActivity(aboutProject);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.image_project_detail_walkthrough:
                if (data.getResult().getWalkThroughURL() != null && data.getResult().getWalkThroughURL().length() > 0) {
                    Intent walkthrough = new Intent(ProjectDetailsActivity.this, GalleryActivity.class);
                    walkthrough.putExtra("POSITION", 2);
                    walkthrough.putExtra("WalkThroughURL", data.getResult().getWalkThroughURL());
                    walkthrough.putParcelableArrayListExtra("Architecture", data.getResult().getGallery().get(0).getArchitecture());
                    walkthrough.putParcelableArrayListExtra("Construction", data.getResult().getGallery().get(0).getConstruction());
                    startActivity(walkthrough);
                    overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                } else {
                   mUtils.toastAlert(this, getString(R.string.no_video_available));
                }
                break;
            case R.id.image_project_detail_call:
//              mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                mUtils.openCallScreen(this, data.getResult().getContactNo());
                break;
            case R.id.image_project_detail_location:

                String uri = "http://maps.google.com/maps?q=loc:" + data.getResult().getLat() + "," + data.getResult().getLong() + " (Current Project)";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
                break;
            case R.id.image_project_detail_360_view:

                if (cd.isConnectingToInternet()) {
                    if (data.getResult().getView360() != null &&
                            data.getResult().getView360().length() > 0 &&
                            data.getResult().getView360().contains("http")) {
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(data.getResult().getView360()));
                        startActivity(browserIntent);
                    } else {
                        mUtils.toastAlert(this, getString(R.string.no_360_video_available));
                    }
                } else {
                    mUtils.toastAlert(this, getString(R.string.no_internet));
                }

                break;
            case R.id.image_project_detail_interested:

                if (cd.isConnectingToInternet()) {
                    if (data.getResult().getInterestedIn().equalsIgnoreCase("false")) {

                        imageprojectdetailinterested.startAnimation(pulse);
                        imageprojectdetailinterested
                                .setImageResource(R.drawable.ic_heart_red);
                        data.getResult().setInterestedIn("True");
                        EventBus.getDefault().post(new Interest("True", data.getResult().getProjectID(), "Detail", 0));

                        mUtils.toastInfo(ProjectDetailsActivity.this, "Thank you for showing Interest");
                        apiProjectInterestedIn(data.getResult().getProjectID(), "True");

                    } else if (data.getResult().getInterestedIn().equalsIgnoreCase("true")) {

                        imageprojectdetailinterested.startAnimation(pulse);
                        imageprojectdetailinterested
                                .setImageResource(R.drawable.ic_heart_white);
                        data.getResult().setInterestedIn("False");
                        EventBus.getDefault().post(new Interest("False", data.getResult().getProjectID(), "Detail", 0));
                        apiProjectInterestedIn(data.getResult().getProjectID(), "False");
                    }

//                    dBhelper.insertintoPROJECTDETAILSTable(data.getResult().getProjectID(), new Gson().toJson(data), "");

                } else {
                    mUtils.toastAlert(ProjectDetailsActivity.this, getString(R.string.no_internet));
                }

                break;

            case R.id.linear_project_online_blocking:
                mUtils.toastAlert(this, "Coming Soon");
                break;
        }
    }

    private void apiProjectInterestedIn(String projId, String value) {
        ProjectInterestedInReq obj = new ProjectInterestedInReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID, projId, value);
        ProjectDetailsPresenter presenter = new ProjectDetailsPresenter(mainModel, this);
        presenter.getProjectInterestedIn(mContext, obj, ApiNames.ProjectInterestedIn);
    }

    @Override
    public void getProjectDetailsSuccess(ProjectDetailsRes projectDetailsRes) {
        nsvprojectdetail.setVisibility(View.VISIBLE);
        txtprojectsdetail.setVisibility(View.GONE);
        data = projectDetailsRes;
        setDataOnViews();


    }

    private void setDataOnViews() {
        mUtils.loadImageInImageview(this, projectImage, imageprojectdetailproject);

        if (data.getResult().getInterestedIn().equalsIgnoreCase("true")) {
            imageprojectdetailinterested.setImageResource(R.drawable.ic_heart_red);
        } else {
            imageprojectdetailinterested.setImageResource(R.drawable.ic_heart_white);
        }

        if (TextUtils.isEmpty(data.getResult().getView360())) {
            imageprojectdetail360view.setVisibility(View.GONE);
        } else {
            imageprojectdetail360view.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(data.getResult().getWalkThroughURL())) {
            imageprojectdetailwalkthrough.setVisibility(View.GONE);
        } else {
            imageprojectdetailwalkthrough.setVisibility(View.VISIBLE);
        }

        textprojectdetailprojectname.setText(data.getResult().getProjectName());
        textprojectdetailprojectlocation.setText(data.getResult().getLocalArea());

//                textProjectDetailProjectLocation.setText(data.getResult().getLocalArea()+", "+data.getResult().getCityName());
        textprojectdetailprojecttype.setText(data.getResult().getApartmentType());
        if (data.getResult().getMinBudget().equals("0")) {
            relativestartfrom.setVisibility(View.GONE);
        } else {
            relativestartfrom.setVisibility(View.VISIBLE);
            textprojectdetailprojectprice.setText(data.getResult().getMinBudget());
        }
    }

    @Override
    public void showEmptyView() {
        nsvprojectdetail.setVisibility(View.GONE);
        txtprojectsdetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void getProjectInterestedIn(CommonRes commonRes) {

    }

    @Override
    public void resetInterestedImage() {
        if (data.getResult().getInterestedIn().equalsIgnoreCase("false")) {
            imageprojectdetailinterested
                    .setImageResource(R.drawable.ic_heart_red);
            data.getResult().setInterestedIn("True");
        } else {
            imageprojectdetailinterested
                    .setImageResource(R.drawable.ic_heart_white);
            data.getResult().setInterestedIn("False");
        }
    }
}
