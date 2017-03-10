package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityFeedbackBinding;
import com.example.smartrealitymodules.models.request.InsertUpdateFeedbackReq;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.FeedbackPresenter;
import com.example.smartrealitymodules.mvp.view.FeedBackView;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by user on 9/3/17.
 */

public class FeedBackActivity extends BaseActivity implements FeedBackView {
    @Inject
    public MainModel mainModel;
    private ActivityFeedbackBinding binding;
    private ArrayList<String> id;
    private ArrayList<String> type;
    private FeedbackPresenter presenter;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        mContext = this;

        getIntentValues();
        renderView();
        init();
    }

    private void init() {
        if(id != null && id.size()>0 && type !=null && type.size()>0) {

            ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, type);
            typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinFeedbackType.setAdapter(typeAdapter);
        }

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.validateFeedback(mContext, id, binding.spinFeedbackType.getSelectedItemPosition(), binding.edittextFeedbackTitle.getText().toString(), binding.edittextFeedbackDescription.getText().toString());
            }
        });
    }

    private void renderView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feedback);
        presenter = new FeedbackPresenter(mainModel,this);
    }

    public void getIntentValues() {
        id = getIntent().getStringArrayListExtra("id");
        type = getIntent().getStringArrayListExtra("type");

    }

    @Override
    public void apiInsertUpdateFeedback(String typeid) {
        if (isConnected) {
            InsertUpdateFeedbackReq obj = new InsertUpdateFeedbackReq(Constants.PROJECTCODE, userType,
                    userId, "0", typeid,binding.edittextFeedbackTitle.getText().toString(), binding.edittextFeedbackDescription.getText().toString());
            presenter.getInsertUpdateFeedback(mContext,obj, ApiNames.InsertUpdateFeedback);
        }else{
            showToast(getString(R.string.no_internet));
        }
    }

    @Override
    public void finishActivity() {
        onBackPressed();
    }
}
