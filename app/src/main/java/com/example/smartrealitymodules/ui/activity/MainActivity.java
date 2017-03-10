package com.example.smartrealitymodules.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.ui.adapter.MainActivityAdapter;

import java.util.ArrayList;


/**
 * Created by user on 13/2/17.
 */

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView rvmain;
    private Toolbar toolbar;
    private ArrayList<String> mainList;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;


        renderView();
        init();




    }

    private void init() {
        mainList = new ArrayList<>();
        mainList.add("Refer a friend");
        mainList.add("Offers");
        mainList.add("Notification");
        mainList.add("Contact Us");
        mainList.add("About Us");
        mainList.add("Share");
        mainList.add("Projects");
        mainList.add("User Profile");
        mainList.add("Login");
        mainList.add("Feedback History");
        mainList.add("Complaint History");
        mainList.add("Splash Fade In");
        mainList.add("Splash Video");
        mainList.add("Resources");

        rvmain.setHasFixedSize(true);
        rvmain.setAdapter(new MainActivityAdapter(mContext,mainList, R.layout.item));
        rvmain.setLayoutManager(new LinearLayoutManager(this));
        rvmain.setItemAnimator(new DefaultItemAnimator());
    }

    private void renderView() {
        setContentView(R.layout.activity_main);
        rvmain = (RecyclerView) findViewById(R.id.rv_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null) {
            getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

    }
        // TODO: 3/3/17 navigation view click listener implementation remaining

}
