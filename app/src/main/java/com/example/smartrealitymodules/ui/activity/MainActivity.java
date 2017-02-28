package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.ui.adapter.MainActivityAdapter;

import java.util.ArrayList;

/**
 * Created by user on 13/2/17.
 */

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        ArrayList<String> mainList = new ArrayList<>();
        mainList.add("Refer a friend");
        mainList.add("Offers");
        mainList.add("Notification");
        mainList.add("Contact Us");
        mainList.add("About Us");
        mainList.add("Share");
        mainList.add("Projects");
        setContentView(R.layout.activity_main);
        RecyclerView rvmain = (RecyclerView) findViewById(R.id.rv_main);
        rvmain.setHasFixedSize(true);
        rvmain.setAdapter(new MainActivityAdapter(mContext,mainList, R.layout.item));
        rvmain.setLayoutManager(new LinearLayoutManager(this));
        rvmain.setItemAnimator(new DefaultItemAnimator());



    }
}
