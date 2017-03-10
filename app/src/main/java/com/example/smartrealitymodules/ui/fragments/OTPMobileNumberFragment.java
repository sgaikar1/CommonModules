package com.example.smartrealitymodules.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.FragmentOtpMobileNumberBinding;
import com.example.smartrealitymodules.models.request.VerifyMobileNumberRequest;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.OTPMobileNumberFragmentPresenter;
import com.example.smartrealitymodules.mvp.view.OTPMobileNumberFragmentView;
import com.example.smartrealitymodules.ui.base.BaseFragment;

import javax.inject.Inject;


/**
 * Created by user on 9/3/17.
 */
public class OTPMobileNumberFragment extends BaseFragment implements OTPMobileNumberFragmentView {
    private final FragmentReplace fragmentReplace;


    @Inject
    public MainModel mainModel;
    private FragmentOtpMobileNumberBinding binding;
    private OTPMobileNumberFragmentPresenter presenter;
    private String otp_mobile_number = "";

    public OTPMobileNumberFragment(FragmentReplace fragmentReplace) {
        this.fragmentReplace =fragmentReplace;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDeps().inject(this);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_otp_mobile_number,container,false);

        presenter = new OTPMobileNumberFragmentPresenter(mainModel, this);

        binding.edtOtpMobileNumber.setText("+91 ");
        Selection.setSelection(binding.edtOtpMobileNumber.getText(), binding.edtOtpMobileNumber.getText().length());
        binding.edtOtpMobileNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().contains("+91 ")) {
                    binding.edtOtpMobileNumber.setText("+91 ");
                    Selection.setSelection(binding.edtOtpMobileNumber.getText(), binding.edtOtpMobileNumber.getText().length());
                }
            }
        });

        binding.tvOtpNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateNumber(binding.edtOtpMobileNumber.getText().toString());
            }
        });


        return binding.getRoot();
    }


    private void verify_mobile_no(String mobile) {

        VerifyMobileNumberRequest obj = new VerifyMobileNumberRequest(mobile);
        presenter.verifyMobileNumber(getActivity(),obj, ApiNames.RequestOTP);

    }

    @Override
    public void validatedNumber(String otp_mobile_number) {
        verify_mobile_no(otp_mobile_number);
        this.otp_mobile_number = otp_mobile_number;
    }

    @Override
    public void navigateToVerifyOtpScreen() {
        fragmentReplace.onClickReplaceFragment(otp_mobile_number);
    }


    public interface FragmentReplace{
        void onClickReplaceFragment(String otp_mobile_number);
    }
}
