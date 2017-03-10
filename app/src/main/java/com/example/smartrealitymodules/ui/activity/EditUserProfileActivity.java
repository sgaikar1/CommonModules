package com.example.smartrealitymodules.ui.activity;

/**
 * Created by user on 8/3/17.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.AdapterView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityEditUserProfileBinding;
import com.example.smartrealitymodules.models.request.EditProfileReq;
import com.example.smartrealitymodules.models.request.MyProfileReq;
import com.example.smartrealitymodules.models.response.MyProfileRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.EditUserProfilePresenter;
import com.example.smartrealitymodules.mvp.view.EditUserProfileView;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.ui.fragments.CameraAndGalleryBottomSheetDialogFragment;
import com.example.smartrealitymodules.utils.Constants;

import java.io.File;

import javax.inject.Inject;

import static com.example.smartrealitymodules.R.id.edittext_user_profile_dob;

/**
 * Created by user on 3/3/17.
 */

public class EditUserProfileActivity extends BaseActivity implements CameraAndGalleryBottomSheetDialogFragment.SetImagePreviewInterface, EditUserProfileView, View.OnClickListener {
    @Inject
    public MainModel mainModel;
    private Context mContext;
    private ActivityEditUserProfileBinding binding;
    private EditUserProfilePresenter presenter;
    private String base64;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);

        renderView();
        apiMyProfile();
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_user_profile);

        binding.btnSave.setOnClickListener(this);
        binding.imgUserProfile.setOnClickListener(this);
        binding.edittextUserProfileDob.setOnClickListener(this);
        binding.edittextUserProfileAnniversaryDob.setOnClickListener(this);

        binding.spinUserProfileMaritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position ==0 ){
                    binding.textinputUserProfileAnniversaryDob.setVisibility(View.GONE);
                }else{
                    binding.textinputUserProfileAnniversaryDob.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        presenter = new EditUserProfilePresenter(mainModel, this);

    }

    private void apiMyProfile() {
        if (isConnected) {

            MyProfileReq obj = new MyProfileReq(userId, userType);
            presenter.getMyProfile(mContext, obj, ApiNames.MyProfile);

        } else {
            mUtils.toastAlert(this, getString(R.string.no_internet));
            // TODO: 8/3/17 Load from database
//            setOfflineData(dBhelper.getValuesFromModule(dBhelper.MYPROFILE));
        }
    }

    @Override
    public void setDataOnViews(MyProfileRes myProfileRes) {
        binding.setResult(myProfileRes.getResult());
    }

    @Override
    public void setImage(Bitmap orientedImage, String base64) {
        binding.imgUserProfile.setImageBitmap(orientedImage);
        this.base64= base64;
    }

    @Override
    public void finishActivity() {
        onBackPressed();
    }

    @Override
    public void apiEditProfileReq() {
        if (isConnected) {

            EditProfileReq obj = new EditProfileReq(Constants.PROJECTCODE, userType, userId,
                    binding.edittextUserProfileName.getText().toString().trim(),
                    binding.edittextUserProfileMobileNumber.getText().toString(),
                    binding.edittextUserProfileAltMobileNumber.getText().toString(),
                    binding.edittextUserProfileEmail.getText().toString(),
                    binding.edittextUserProfileDob.getText().toString(),
                    binding.spinUserProfileMaritalStatus.getSelectedItemPosition() == 0 ? "" : binding.edittextUserProfileAnniversaryDob.getText().toString(),
                    binding.edittextUserProfileAddress.getText().toString(),
                    binding.edittextUserProfilePincode.getText().toString(),
                    binding.spinUserProfileMaritalStatus.getSelectedItem().toString(),
                    base64);
            presenter.getEditMyProfile(mContext, obj, ApiNames.EditProfile);
        } else {
            mUtils.toastAlert(this, getString(R.string.no_internet));
        }
    }

    @Override
    public void setUserDob(String dob) {
        binding.edittextUserProfileDob.setText(dob);
    }

    @Override
    public void setAnniversary(String anniversary) {
        binding.edittextUserProfileAnniversaryDob.setText(anniversary);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_user_profile:

                // TODO: 8/3/17 Implement Storage and Camera permission
                BottomSheetDialogFragment bottomSheetDialogFragment = (BottomSheetDialogFragment) CameraAndGalleryBottomSheetDialogFragment.newInstance(EditUserProfileActivity.this);
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                break;
            case R.id.btn_save:

                presenter.validateForm(mContext,
                        binding.edittextUserProfileAltMobileNumber.getText().toString(),
                        binding.spinUserProfileMaritalStatus.getSelectedItemPosition(),
                        binding.edittextUserProfileAnniversaryDob.getText().toString(),
                        binding.edittextUserProfilePincode.getText().toString());

                break;
            case edittext_user_profile_dob:
                presenter.setUserDob(mContext);

                break;
            case R.id.edittext_user_profile_anniversary_dob:
                presenter.setAnniversary(mContext);
                break;
        }
    }

    @Override
    public void getImagePreviewCamera(File file) {
        presenter.convertFileToBitmap(file);
    }
}
