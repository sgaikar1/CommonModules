package com.example.smartrealitymodules.ui.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.FragmentOtpCodeBinding;
import com.example.smartrealitymodules.models.request.VerifyMobileNumberRequest;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.OTPCodeFragmentPresenter;
import com.example.smartrealitymodules.mvp.view.OTPCodeFragmentView;
import com.example.smartrealitymodules.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by user on 9/3/17.
 */
public class OTPCodeFragment extends BaseFragment implements OTPCodeFragmentView {

    @Inject
    public MainModel mainModel;
    private FragmentOtpCodeBinding binding;
    Boolean is_mesg_recevied = false;
    public int seconds = 60;
    EditText edt_otp_mobile_number;
    String deviceTokenUpdated = "";
    ProgressBar progressBar;
    private OTPCodeFragmentPresenter presenter;
    private String mobile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDeps().inject(this);

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_otp_code,container,false);

        getIntentValues();

        // Register the sms otp retriveing boardcast
        IntentFilter filter = new IntentFilter(
                "android.provider.Telephony.SMS_RECEIVED");
        getActivity().registerReceiver(mBroadcastReceiver, filter);

        presenter = new OTPCodeFragmentPresenter(mainModel, this);



        binding.tvOtpDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 9/3/17 OTP VERIFICATION module not completed(SMS receiver not implemented also widgets enable/disable not handled)
                // TODO: 9/3/17 tell backend team to create new request pojo for verifyOTP and then implement functionality below
                presenter.VerifyOtpOnServer();
                /*if (!edt_otp_mobile_number.getText().toString().equalsIgnoreCase("")) {
                    if (edt_otp_mobile_number.getText().length() == 5) {

                        progressBar.setVisibility(View.GONE);

                        pDialog.setMessage("Please wait...");
                        pDialog.setCancelable(false);
                        pDialog.show();

                        // Sign up/Login screen or Social Media
                        if (from.equalsIgnoreCase("signup")) {
                            callAPISignUp(profileImageUrl, name, mobile, edt_otp_mobile_number.getText().toString());
                        } else if (from.equalsIgnoreCase("socialmedia")) {
                            // Social Media
                            callAPIVerifyUserRequest(fromSocialMedia, id, profileImageUrl, name, email, mobile, edt_otp_mobile_number.getText().toString());

                        } else if (from.equalsIgnoreCase("login")) {
                            callAPILogin(Utils.Nagrik, mobile, edt_otp_mobile_number.getText().toString());
                        }
                    } else {
                        Utils.makeToast(getActivity(), "Invalid OTP");
                    }
                } else {
                    Utils.makeToast(getActivity(), "Please enter OTP");
                }*/
            }
        });

        binding.tvRegenerateOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call API
                VerifyMobileNumberRequest obj = new VerifyMobileNumberRequest(mobile);
                presenter.verifyMobileNumber(getActivity(),obj, ApiNames.RequestOTP);
            }
        });

        return binding.getRoot();
    }

  /*

    private void showEnterOTP() {
//        timer_auto.cancel();
        tv_regenerate_otp.setClickable(true);
        tv_regenerate_otp.setTextColor(getActivity().getResources().getColor(R.color.blue_otp));
        tv_otp_timer.setTextColor(getActivity().getResources().getColor(R.color.gray_draker_content));
//        edt_otp_mobile_number.setEnabled(false);
//        edt_otp_mobile_number.setInputType(InputType.TYPE_NULL);
    }


    private void hideEnterOTP() {
        tv_regenerate_otp.setClickable(false);
        tv_regenerate_otp.setTextColor(getActivity().getResources().getColor(R.color.gray_draker));
        tv_otp_timer.setTextColor(getActivity().getResources().getColor(R.color.gray_draker));
//        edt_otp_mobile_number.setEnabled(false);
//        edt_otp_mobile_number.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }*/

    final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // Retrieves a map of extended data from the intent.
            mUtils.logMe("Message", "received");
            final Bundle bundle = intent.getExtras();
            try {
                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage
                                .createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage
                                .getDisplayOriginatingAddress();

                        String senderNum = phoneNumber;
                        String message = currentMessage
                                .getDisplayMessageBody();

                        if (!is_mesg_recevied) {
                            if (senderNum.contains("SMARTX")) {
                                try {

                                    progressBar.setVisibility(View.GONE);

                                    String otp_main = message.substring(
                                            message.length() - 5);

                                    edt_otp_mobile_number.setText(otp_main);
                                    is_mesg_recevied = true;


                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                } // bundle is null
            } catch (Exception e) {
                mUtils.logMe("SmsReceiver", "Exception smsReceiver" + e);
            }
        }
    };

    @Override
    public void GotoHome() {
        showToast("Otp Verificatiopn Successful you can implement next activity route here");
    }

    public void getIntentValues() {
        Bundle extras = getArguments();
        mobile = extras.getString("mobile", "");

    }

  /*  // for Android, you should also log app deactivation
    @Override
    public void onPause() {
        super.onPause();
        try {
            getActivity().unregisterReceiver(mBroadcastReceiver);
        } catch (Exception e) {

        }
    }

//    private void verify_mobile_no(String mobile) {
//        verify_mobile_noRequest obj = new verify_mobile_noRequest(ApiNames.VERIFY_MOBILE_NO, mobile);
//        SuperWebApiCall objAPI = new SuperWebApiCall(getActivity(), OTPCodeFragment.this, obj, ApiNames.VERIFY_MOBILE_NO);
//        objAPI.apiSelector();
//    }

    private void callAPISignUp(String profile_pic, String name, String mobile, String otp) {

        deviceTokenUpdated = mSession.getDeviceToken();

        signupRequest obj = new signupRequest(ApiNames.SIGNUP, profile_pic, name, mobile, otp, deviceTokenUpdated, "Android");

        SuperWebApiCall objAPI = new SuperWebApiCall(getActivity(), OTPCodeFragment.this, obj, ApiNames.SIGNUP);
        objAPI.apiSelector();
    }

    private void callAPISocialMedia(String socialMediaType, String id, String profile_pic, String name, String emailid, String mobile, String otp) {

        deviceTokenUpdated = mSession.getDeviceToken();

//        Social_media_loginRequest obj = new Social_media_loginRequest(ApiNames.SOCIAL_MEDIA_LOGIN, socialMediaType, "Nagrik", id,
//                profile_pic, name, emailid, deviceTokenUpdated, "Android", mobile ,otp);

//        SuperWebApiCall objAPI = new SuperWebApiCall(getActivity(), OTPCodeFragment.this, obj, ApiNames.SOCIAL_MEDIA_LOGIN);
//        objAPI.apiSelector();
    }

    private void callAPIVerifyUserRequest(String socialMediaType, String id, String profile_pic, String name, String emailid, String mobile, String otp) {

        deviceTokenUpdated = mSession.getDeviceToken();

        Verify_user_request obj = new Verify_user_request(ApiNames.VERIFY_USER, socialMediaType, "Nagrik", id,
                profile_pic, name, emailid, mobile, otp, deviceTokenUpdated, "Android");

        SuperWebApiCall objAPI = new SuperWebApiCall(getActivity(), OTPCodeFragment.this, obj, ApiNames.VERIFY_USER);
        objAPI.apiSelector();
    }

    private void callAPILogin(String userType, String mobile, String otp) {
        deviceTokenUpdated = mSession.getDeviceToken();

        loginRequest obj = new loginRequest(ApiNames.LOGIN, userType, //Nagrik
                "", "", deviceTokenUpdated, "Android", mobile, otp);

        SuperWebApiCall objAPI = new SuperWebApiCall(getActivity(), OTPCodeFragment.this, obj, ApiNames.LOGIN);
        objAPI.apiSelector();
    }

    private void verify_mobile_no(String mobile) {
        progressBar.setVisibility(View.VISIBLE);
        verify_mobile_noRequest obj = new verify_mobile_noRequest(ApiNames.VERIFY_MOBILE_NO, mobile);
        SuperWebApiCall objAPI = new SuperWebApiCall(getActivity(), OTPCodeFragment.this, obj, ApiNames.VERIFY_MOBILE_NO);
        objAPI.apiSelector();
    }

    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase(ApiNames.SIGNUP)) {

            if (!flagToCheckFailure) {
                loginResponse data = (loginResponse) response;

                if (data != null) {

                    if (data.isStatus()) {

                        if (data.getData() != null) {

                            boolean exceptionToastFlag = false;

                            if (data.getData().getAccess_token() != null) {
                                if (!data.getData().getAccess_token().equalsIgnoreCase("")) {
                                    mSession.setUserid(data.getData().getAccess_token());
//                                    if (data.getData().getUser_type().equalsIgnoreCase(Utils.Nagrik)) {
                                    Intent in = new Intent(getActivity(), HomeRevisedActivity.class);
                                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(in);
                                    getActivity().finish();
//                                    } else {
//                                        startActivity(new Intent(LogInActivity.this, AdminTimelineActivity.class));
//                                        finish();
//                                    }
                                } else {
                                    exceptionToastFlag = true;
                                }
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_id() != null) {
                                mSession.setUseridForChat(data.getData().getUser_id());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_name() != null) {
                                mSession.setUsername(data.getData().getUser_name());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_img() != null) {
                                mSession.setProfileImage(data.getData().getUser_img());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_address() != null) {
                                mSession.setKeyAddress(data.getData().getUser_address());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_mobile() != null) {
                                mSession.setKeyMobile(data.getData().getUser_mobile());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_email() != null) {
                                mSession.setKeyEmailId(data.getData().getUser_email());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getAbout_user() != null) {
                                mSession.setKeyAbout(data.getData().getAbout_user());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getCity() != null) {
                                mSession.setKeyCity(data.getData().getCity());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getState() != null) {
                                mSession.setKeyState(data.getData().getState());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getCountry() != null) {
                                mSession.setKeyCountry(data.getData().getCountry());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getPincode() != null) {
                                mSession.setKeyPincode(data.getData().getPincode());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getDob() != null) {
                                mSession.setKeyDob(data.getData().getDob());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getGender() != null) {
                                mSession.setKeyGender(data.getData().getGender());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_type() != null) {
                                mSession.setUserType(data.getData().getUser_type());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (exceptionToastFlag) {
//                                Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                                Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                            }

                        } else {
//                            Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                            Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                        }

                    } else {

                        pDialog.dismiss();

                        if (data.getMessage() != null) {
//                            Utils.makeToast(LogInActivity.this, data.getMessage());
                            Utils.info(getActivity(), edt_otp_mobile_number, data.getMessage(), false);

                        } else {
//                            Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                            Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                        }
                    }

                } else {
//                    Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                    Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                }

            } else {
//                Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
            }
        } else if (webServiceName.equalsIgnoreCase(ApiNames.VERIFY_MOBILE_NO)) {
            if (!flagToCheckFailure) {
                verify_mobile_noResponse data = (verify_mobile_noResponse) response;
                if (data != null) {
                    pDialog.dismiss();
                    if (data.isStatus()) {

                        // Open OTP section with otp entering screen
//                        hideEnterOTP();
//                        seconds = 60;
//                        timer_auto.scheduleAtFixedRate(new TimerTask() {
//                            @Override
//                            public void run() {
//                                getActivity().runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        if (seconds != 0) {
//                                            tv_otp_timer.setText(String.valueOf(seconds));
//                                            seconds -= 1;
//                                        } else {
//                                            tv_otp_timer.setText(String.valueOf(seconds));
//                                            showEnterOTP();
//                                        }
//                                    }
//                                });
//                            }
//                        }, 0, 1000);

                        if (data.getMessage() != null) {
                            Utils.makeToast(getActivity(), data.getMessage());
                        }

                    } else {

                        if (data.getMessage() != null) {
                            Utils.makeToast(getActivity(), data.getMessage());
                            //Utils.info(getActivity(), edt_otp_mobile_number, data.getMessage(), false);
                        } else {
                            Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                        }
                    }
                } else {
                    Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                }
            } else {
                Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
            }
        } else if (webServiceName.equalsIgnoreCase(ApiNames.VERIFY_USER)) {
            if (!flagToCheckFailure) {
                Verify_user_response data = (Verify_user_response) response;
                if (data != null) {
                    if (data.isStatus()) {
                        if (data.getData() != null) {

                            boolean exceptionToastFlag = false;

                            if (data.getData().getAccess_token() != null) {
                                mSession.setUseridForChat(data.getData().getUser_id());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_img() != null) {
                                mSession.setProfileImage(data.getData().getUser_img());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_name() != null) {
                                mSession.setUsername(data.getData().getUser_name());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_address() != null) {
                                mSession.setKeyAddress(data.getData().getUser_address());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_mobile() != null) {
                                mSession.setKeyMobile(data.getData().getUser_mobile());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_email() != null) {
                                mSession.setKeyEmailId(data.getData().getUser_email());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getAbout_user() != null) {
                                mSession.setKeyAbout(data.getData().getAbout_user());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getCity() != null) {
                                mSession.setKeyCity(data.getData().getCity());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getState() != null) {
                                mSession.setKeyState(data.getData().getState());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getCountry() != null) {
                                mSession.setKeyCountry(data.getData().getCountry());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getPincode() != null) {
                                mSession.setKeyPincode(data.getData().getPincode());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getDob() != null) {
                                mSession.setKeyDob(data.getData().getDob());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getGender() != null) {
                                mSession.setKeyGender(data.getData().getGender());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_type() != null) {
                                if (data.getData().getUser_type().isEmpty()) {
                                    mSession.setUserType("Nagrik");
                                } else {
                                    mSession.setUserType(data.getData().getUser_type());
                                }
                            } else {
                                mSession.setUserType("Nagrik");
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getAccess_token() != null) {
                                if (!data.getData().getAccess_token().equalsIgnoreCase("")) {
                                    mSession.setUserid(data.getData().getAccess_token());
                                    pDialog.dismiss();

                                    // Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK

                                    Intent in = new Intent(getActivity(), HomeRevisedActivity.class);
                                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(in);
                                    getActivity().finish();
                                } else {
                                    exceptionToastFlag = true;
                                }
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (exceptionToastFlag) {
//                                Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                                pDialog.dismiss();
                                Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                            }

                        } else {
//                            Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                            pDialog.dismiss();
                            Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                        }
                    } else {

                        if (data.getMessage() != null) {
//                            Utils.makeToast(LogInActivity.this, data.getMessage());
                            pDialog.dismiss();
                            Utils.info(getActivity(), edt_otp_mobile_number, data.getMessage(), false);
                        } else {
//                            Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                            pDialog.dismiss();
                            Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                        }
                    }

                } else {
//                    Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                    pDialog.dismiss();
                    Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                }
            } else {
//                Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                pDialog.dismiss();
                Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
            }
        } else if (webServiceName.equalsIgnoreCase(ApiNames.LOGIN)) {

            if (!flagToCheckFailure) {
                loginResponse data = (loginResponse) response;

                if (data != null) {

                    if (data.isStatus()) {

                        if (data.getData() != null) {

                            boolean exceptionToastFlag = false;

                            if (data.getData().getAccess_token() != null) {
                                if (!data.getData().getAccess_token().equalsIgnoreCase("")) {
                                    mSession.setUserid(data.getData().getAccess_token());
//                                    if (data.getData().getUser_type().equalsIgnoreCase(Utils.Nagrik)) {
                                    Intent in = new Intent(getActivity(), HomeRevisedActivity.class);
                                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(in);
                                    getActivity().finish();
//                                    } else {
//                                        startActivity(new Intent(LogInActivity.this, AdminTimelineActivity.class));
//                                        finish();
//                                    }
                                } else {
                                    exceptionToastFlag = true;
                                }
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_id() != null) {
                                mSession.setUseridForChat(data.getData().getUser_id());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_name() != null) {
                                mSession.setUsername(data.getData().getUser_name());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_img() != null) {
                                mSession.setProfileImage(data.getData().getUser_img());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_address() != null) {
                                mSession.setKeyAddress(data.getData().getUser_address());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_mobile() != null) {
                                mSession.setKeyMobile(data.getData().getUser_mobile());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_email() != null) {
                                mSession.setKeyEmailId(data.getData().getUser_email());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getAbout_user() != null) {
                                mSession.setKeyAbout(data.getData().getAbout_user());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getCity() != null) {
                                mSession.setKeyCity(data.getData().getCity());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getState() != null) {
                                mSession.setKeyState(data.getData().getState());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getCountry() != null) {
                                mSession.setKeyCountry(data.getData().getCountry());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getPincode() != null) {
                                mSession.setKeyPincode(data.getData().getPincode());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getDob() != null) {
                                mSession.setKeyDob(data.getData().getDob());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getGender() != null) {
                                mSession.setKeyGender(data.getData().getGender());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (data.getData().getUser_type() != null) {
                                mSession.setUserType(data.getData().getUser_type());
                            } else {
                                exceptionToastFlag = true;
                            }

                            if (exceptionToastFlag) {
//                                Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                                Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                            }

                        } else {
//                            Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                            Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                        }

                    } else {

                        pDialog.dismiss();

                        if (data.getMessage() != null) {
//                            Utils.makeToast(LogInActivity.this, data.getMessage());
                            Utils.info(getActivity(), edt_otp_mobile_number, data.getMessage(), false);

                        } else {
//                            Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                            Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                        }
                    }

                } else {
//                    Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                    Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
                }

            } else {
//                Utils.makeToast(LogInActivity.this, Utils.Exception_text);
                Utils.info(getActivity(), edt_otp_mobile_number, Utils.Exception_text, false);
            }
        }
    }*/
}

