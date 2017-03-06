package com.example.smartrealitymodules.ui.base;

import android.app.Application;

import com.zplesac.connectionbuddy.ConnectionBuddy;
import com.zplesac.connectionbuddy.ConnectionBuddyConfiguration;

/**
 * Created by user on 3/3/17.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ConnectionBuddyConfiguration networkInspectorConfiguration = new ConnectionBuddyConfiguration.Builder(this).build();
        ConnectionBuddy.getInstance().init(networkInspectorConfiguration);
    }
}
