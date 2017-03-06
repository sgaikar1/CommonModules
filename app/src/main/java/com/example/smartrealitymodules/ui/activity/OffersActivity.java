package com.example.smartrealitymodules.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.databinding.ActivityOffersBinding;
import com.example.smartrealitymodules.databinding.DialogMessageBinding;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.response.GetOffersRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.OffersPresenter;
import com.example.smartrealitymodules.mvp.view.OffersView;
import com.example.smartrealitymodules.ui.adapter.OffersAdapter;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;

import javax.inject.Inject;

/**
 * Created by user on 13/2/17.
 */


public class OffersActivity extends BaseActivity implements OffersView{

    @Inject
    public MainModel mainModel;
    private Dialog alert;
    ActivityOffersBinding binding;
    private OffersPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();
        presenter = new OffersPresenter(mainModel, this);

        if (isConnected) {
            CommonReq obj = new CommonReq(Constants.PROJECTCODE, Constants.USERTYPE, Constants.USERID);
            presenter.getOffersList(obj, ApiNames.GetOffers);
        }else{
            removeWait();
            mUtils.toastAlert(this, getString(R.string.no_internet));
            // TODO: 20/2/17 If internet not available display data from local database
//            setOfflineData(dBhelper.getValuesFromModule(dBhelper.OFFERS));
        }
    }

    public  void renderView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_offers);
        setProgressBar();
    }

    public void init(){
        binding.list.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void getOffersListSuccess(GetOffersRes getOffersRes) {
        OffersAdapter adapter = new OffersAdapter(OffersActivity.this, getOffersRes.getResult(),
                new OffersAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(GetOffersRes.Result item, View v) {
                        showDialog(item);
                    }
                });

        binding.list.setAdapter(adapter);
    }

    @Override
    public void dissmissDialog() {
        alert.dismiss();
    }


    private void showDialog(GetOffersRes.Result item) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alert = new Dialog(this, R.style.FullHeightDialog);
        DialogMessageBinding binding = DataBindingUtil.inflate(inflater,R.layout.dialog_message,null,false);
        alert.setContentView(binding.getRoot());
        alert.setCancelable(true);
        alert.show();

        binding.setOffers(item);
        binding.setOffersPresenter(presenter);

        Linkify.addLinks(binding.txtDialogMsg, Linkify.PHONE_NUMBERS);


    }

}
