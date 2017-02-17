package com.example.smartrealitymodules.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.CityListData;
import com.example.smartrealitymodules.models.CityListResponse;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.ui.adapter.HomeAdapter;
import com.example.smartrealitymodules.mvp.presenter.HomePresenter;
import com.example.smartrealitymodules.mvp.view.HomeView;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeView {

    private RecyclerView list;
    @Inject
    public MainModel mainModel;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        HomePresenter presenter = new HomePresenter(mainModel, this);
        presenter.getCityList();
    }

    public  void renderView(){
        setContentView(R.layout.activity_home);
        list = (RecyclerView) findViewById(R.id.list);
        setProgressBar();
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void getityListSuccess(CityListResponse cityListResponse) {

        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), cityListResponse.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CityListData Item) {
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        list.setAdapter(adapter);

    }

    @Override
    public void showToast(String Message) {

    }
}
