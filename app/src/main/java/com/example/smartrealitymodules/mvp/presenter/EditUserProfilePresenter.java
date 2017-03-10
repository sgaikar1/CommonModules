package com.example.smartrealitymodules.mvp.presenter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.widget.DatePicker;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.EditProfileReq;
import com.example.smartrealitymodules.models.request.MyProfileReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.MyProfileRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.EditUserProfileView;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.ImageOrientationChecker;
import com.example.smartrealitymodules.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 8/3/17.
 */
public class EditUserProfilePresenter {
    private final MainModel mainModel;
    private final EditUserProfileView view;
    private final CompositeSubscription subscriptions;

    public EditUserProfilePresenter(MainModel mainModel, EditUserProfileView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getMyProfile(final Context mContext, MyProfileReq obj, String apiname) {
        view.showProgressDialog();

        Subscription subscription = mainModel.getMyProfile(obj, apiname, new MainModel.GetMyProfileCallback() {
            @Override
            public void onSuccess(MyProfileRes myProfileRes) {
                view.hideProgressDialog();
                view.setDataOnViews(myProfileRes);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.hideProgressDialog();
                view.onFailure(networkError.getAppErrorMessage());
                view.finishActivity();
                view.showToast(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(MyProfileRes myProfileRes, boolean flag) {
                view.hideProgressDialog();
                if (flag) {
                    view.showToast(myProfileRes.getMessage());
                } else {
                    view.showToast(mContext.getString(R.string.data_error));
                }
            }

        });

        subscriptions.add(subscription);
    }

    public void convertFileToBitmap(File file) {
        try {

            Bitmap originalBitmap = BitmapFactory.decodeFile(file.getPath());
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            final int maxSize = 1080;
            int outWidth;
            int outHeight;
            int inWidth = originalBitmap.getWidth();
            int inHeight = originalBitmap.getHeight();
            if (inWidth > inHeight) {
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;
            }
            Bitmap processedImage = Bitmap.createScaledBitmap(originalBitmap, outWidth, outHeight, true);
            Bitmap orientedImage = ImageOrientationChecker.changeOrientation(file.getPath(), processedImage);

            processedImage = orientedImage;

            //Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            processedImage.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();

            Uri tmpURI = Uri.fromFile(file);

            String base64 = Base64.encodeToString(bitmapdata, Base64.NO_WRAP);
            new Utils().logMe("base64", base64);

            view.setImage(orientedImage, base64);
        } catch (Exception e) {
            new Utils().logMe("Exception camera", e.toString());
        }
    }

    public void getEditMyProfile(final Context mContext, EditProfileReq obj, String apiname) {
        view.showProgressDialog();

        Subscription subscription = mainModel.getEditMyProfile(obj, apiname, new MainModel.GetEditMyProfileCallback() {
            @Override
            public void onSuccess(CommonRes commonRes) {
                view.hideProgressDialog();
                view.showToast(commonRes.getMessage());
                view.finishActivity();
            }

            @Override
            public void onError(NetworkError networkError) {
                view.hideProgressDialog();
                view.onFailure(networkError.getAppErrorMessage());
                view.showToast(networkError.getAppErrorMessage());
            }

            @Override
            public void onFailure(CommonRes commonRes, boolean flag) {
                view.hideProgressDialog();
                if (flag) {
                    view.showToast(commonRes.getMessage());
                } else {
                    view.showToast(mContext.getString(R.string.data_error));
                }
            }

        });

        subscriptions.add(subscription);
    }

    public void validateForm(Context mContext, String altMobileNo, int spinnerPos, String anniversary, String pincode) {
        int status = Constants.DEFAULT_STATUS;
        boolean flag = false;
        if (altMobileNo.length() > 0 && altMobileNo.length() < 10) {
            status = Constants.MOBILENO_ALTERNATE_EMPTY_STATUS;
        }else if(spinnerPos == 1 && anniversary.length() ==0){
            status = Constants.ANNIVERSARY_EMPTY_STATUS;
        }else if(pincode.length()>0 && pincode.length()<6){
            status = Constants.PINCODE_EMPTY_STATUS;
        }else {
            flag = true;
        }

        if(flag){
            view.apiEditProfileReq();
        }else{
            switch (status){
                case Constants.MOBILENO_ALTERNATE_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_valid_alt_mobile_no));
                    break;
                case Constants.ANNIVERSARY_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_anniversary_dob));
                    break;
                case Constants.PINCODE_EMPTY_STATUS:
                    view.showToast(mContext.getString(R.string.enter_valid_pincode));
                    break;
            }
        }
    }

    public void setUserDob(Context mContext) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String year1 = String.valueOf(year);
                        String month = String.valueOf(monthOfYear + 1);
                        if (month.length() == 1) {
                            month = "0" + month;
                        }
                        String day = String.valueOf(dayOfMonth);
                        if (day.length() == 1) {
                            day = "0" + day;
                        }
                        EditUserProfilePresenter.this.view.setUserDob(day + "-" + month + "-"
                                + year1);

                    }
                }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();


    }

    public void setAnniversary(Context context) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String year1 = String.valueOf(year);
                        String month = String.valueOf(monthOfYear + 1);
                        if (month.length() == 1) {
                            month = "0" + month;
                        }
                        String day = String.valueOf(dayOfMonth);
                        if (day.length() == 1) {
                            day = "0" + day;
                        }
                       EditUserProfilePresenter.this.view.setAnniversary(day + "-" + month + "-"
                               + year1);

                    }
                }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }
}
