package com.example.smartrealitymodules.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.response.GetOffersRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.OffersPresenter;
import com.example.smartrealitymodules.mvp.view.OffersView;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.ui.adapter.OffersAdapter;
import com.example.smartrealitymodules.utils.Constants;

import javax.inject.Inject;

/**
 * Created by user on 13/2/17.
 */


public class OffersActivity extends BaseActivity implements OffersView, OffersAdapter.OnItemClickListener {

    @Inject
    public MainModel mainModel;
    ProgressBar progressBar;
    private RecyclerView list;
    private Dialog alert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        // TODO: 27/2/17 check internet connection.. do needed check in other activities too
        CommonReq obj = new CommonReq(Constants.PROJECTCODE, Constants.USERTYPE,Constants.USERID);
        OffersPresenter presenter = new OffersPresenter(mainModel, this);
        presenter.getOffersList(obj, ApiNames.GetOffers);
    }

    public  void renderView(){
        setContentView(R.layout.activity_offers);
        list = (RecyclerView) findViewById(R.id.list);
        setProgressBar();
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void getOffersListSuccess(GetOffersRes getOffersRes) {
        OffersAdapter adapter = new OffersAdapter(OffersActivity.this, getOffersRes.getResult(),
                new OffersAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(GetOffersRes.Result Item) {

                    }
                });

        adapter.registerItemClickListener(OffersActivity.this);
        list.setAdapter(adapter);
    }


    private void showDialog(String Title, String Description) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alert = new Dialog(this, R.style.FullHeightDialog);
        View view = inflater.inflate(R.layout.dialog_message, null);
        alert.setContentView(view);
        alert.setCancelable(true);
        alert.show();

        TextView txtHeading, txtMsg;
        ImageView ivClose;
        Button btnOk;

        txtHeading = (TextView) view.findViewById(R.id.txt_dialog_Heading);
        txtMsg = (TextView) view.findViewById(R.id.txt_dialog_msg);
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        btnOk = (Button) view.findViewById(R.id.btnOk);

        txtHeading.setText(Title.toUpperCase());
        txtMsg.setText(Html.fromHtml(Description));

        Linkify.addLinks(txtMsg, Linkify.PHONE_NUMBERS);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.dismiss();
            }
        });

    }

    @Override
    public void onClick(GetOffersRes.Result Item) {
        showDialog(Item.getTitle(), Item.getDescription());
    }
}
