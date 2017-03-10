package com.example.smartrealitymodules.mvp.view;

/**
 * Created by user on 9/3/17.
 */
public interface OTPMobileNumberFragmentView extends BaseView{
    void validatedNumber(String otp_mobile_number);

    void navigateToVerifyOtpScreen();
}
