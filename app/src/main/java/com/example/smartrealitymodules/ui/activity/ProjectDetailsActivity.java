package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityProjectDetailsBinding;
import com.example.smartrealitymodules.models.eventbus.Interest;
import com.example.smartrealitymodules.models.request.ProjectDetailsReq;
import com.example.smartrealitymodules.models.request.ProjectInterestedInReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.ProjectDetailsPresenter;
import com.example.smartrealitymodules.mvp.view.ProjectDetailsView;
import com.example.smartrealitymodules.ui.base.BaseActivity;
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
    private String projectId;
    private String projectName;
    public String projectImage;
    private ProjectDetailsRes data;
    private Animation pulse;
    private ActivityProjectDetailsBinding binding;

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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_project_details);

        binding.setUtils(mUtils);
        binding.setProjectActivity(this);

        pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);

        setProgressBar();

        binding.frameProjectDetailGallery.setOnClickListener(this);
        binding.frameProjectDetailLayout.setOnClickListener(this);
        binding.linearProjectDetailAmenities.setOnClickListener(this);
        binding.linearProjectDetailScheduleSiteVisit.setOnClickListener(this);
        binding.linearProjectDetailAboutProject.setOnClickListener(this);
        binding.imageProjectDetailWalkthrough.setOnClickListener(this);
        binding.imageProjectDetailCall.setOnClickListener(this);
        binding.imageProjectDetailLocation.setOnClickListener(this);
        binding.imageProjectDetailInterested.setOnClickListener(this);
        binding.imageProjectDetail360View.setOnClickListener(this);
        binding.linearProjectOnlineBlocking.setOnClickListener(this);
        // TODO: 10/3/17 change google-services.json file
    }

    private void apiProjectDetails() {
        if (isConnected) {

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

                if (isConnected) {
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

                if (isConnected) {
                    if (data.getResult().getInterestedIn().equalsIgnoreCase("false")) {

                        binding.imageProjectDetailInterested.startAnimation(pulse);
                        binding.imageProjectDetailInterested
                                .setImageResource(R.drawable.ic_heart_red);
                        data.getResult().setInterestedIn("True");
                        EventBus.getDefault().post(new Interest("True", data.getResult().getProjectID(), "Detail", 0));

                        mUtils.toastInfo(ProjectDetailsActivity.this, "Thank you for showing Interest");
                        apiProjectInterestedIn(data.getResult().getProjectID(), "True");

                    } else if (data.getResult().getInterestedIn().equalsIgnoreCase("true")) {

                        binding.imageProjectDetailInterested.startAnimation(pulse);
                        binding.imageProjectDetailInterested
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
        binding.nsvProjectDetail.setVisibility(View.VISIBLE);
        binding.txtProjectsDetail.setVisibility(View.GONE);
        data = projectDetailsRes;
        binding.setProjects(data.getResult());
    }


    @Override
    public void showEmptyView() {
        binding.nsvProjectDetail.setVisibility(View.GONE);
        binding.txtProjectsDetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void getProjectInterestedIn(CommonRes commonRes) {

    }

    @Override
    public void resetInterestedImage() {
        if (data.getResult().getInterestedIn().equalsIgnoreCase("false")) {
            binding.imageProjectDetailInterested
                    .setImageResource(R.drawable.ic_heart_red);
            data.getResult().setInterestedIn("True");
        } else {
            binding.imageProjectDetailInterested
                    .setImageResource(R.drawable.ic_heart_white);
            data.getResult().setInterestedIn("False");
        }
    }
}
