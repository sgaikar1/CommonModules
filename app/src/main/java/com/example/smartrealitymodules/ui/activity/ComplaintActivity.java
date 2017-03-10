package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityComplaintBinding;
import com.example.smartrealitymodules.models.request.InsertUpdateComplaintRequestReq;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.ComplaintPresenter;
import com.example.smartrealitymodules.mvp.view.ComplaintView;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.ui.fragments.CameraAndGalleryBottomSheetDialogFragment;
import com.example.smartrealitymodules.utils.Constants;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by user on 10/3/17.
 */
public class ComplaintActivity extends BaseActivity implements ComplaintView, CameraAndGalleryBottomSheetDialogFragment.SetImagePreviewInterface {
    @Inject
    public MainModel mainModel;
    private Context mContext;
    private ActivityComplaintBinding binding;
    private ComplaintPresenter presenter;
    private String base64;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        mContext = this;

        renderView();
        init();
    }
    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complaint);

        // TODO: 10/3/17 Not tested due to user id of buyer not created at server
        presenter = new ComplaintPresenter(mainModel,this);
    }

    private void init() {

        binding.floatImageComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheetDialogFragment = (BottomSheetDialogFragment) CameraAndGalleryBottomSheetDialogFragment.newInstance(ComplaintActivity.this);
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

            }
        });

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.validateComplaint(mContext,binding.edittextComplaintTitle.getText().toString(),binding.edittextComplaintDescription.getText().toString());

            }
        });
    }


    @Override
    public void apiInsertUpdateFeedback(String title, String desc) {
        if (isConnected) {

            InsertUpdateComplaintRequestReq obj = new InsertUpdateComplaintRequestReq(Constants.PROJECTCODE, userType,
                    userId, "0", title, desc, base64);
            presenter.getInsertUpdateComplaint(mContext,obj, ApiNames.InsertUpdateComplaintRequest);

        } else {
            mUtils.toastAlert(this, getString(R.string.no_internet));
        }
    }

    @Override
    public void setImage(Bitmap orientedImage, String base64) {
        binding.imageComplaint.setImageBitmap(orientedImage);
        this.base64= base64;
    }

    @Override
    public void finishActivity() {
        onBackPressed();
    }

    @Override
    public void getImagePreviewCamera(File file) {
        presenter.convertFileToBitmap(file);
    }
}
