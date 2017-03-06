package com.example.smartrealitymodules.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.NetworkModule;
import com.example.smartrealitymodules.dependancy.DaggerDependancyInjection;
import com.example.smartrealitymodules.dependancy.DependancyInjection;
import com.example.smartrealitymodules.mvp.view.BaseView;
import com.example.smartrealitymodules.utils.Utils;
import com.zplesac.connectionbuddy.ConnectionBuddy;
import com.zplesac.connectionbuddy.cache.ConnectionBuddyCache;
import com.zplesac.connectionbuddy.interfaces.ConnectivityChangeListener;
import com.zplesac.connectionbuddy.models.ConnectivityEvent;
import com.zplesac.connectionbuddy.models.ConnectivityState;

import java.io.File;

/**
 * Created by ennur on 6/28/16.
 */
public class BaseActivity extends AppCompatActivity implements BaseView, ConnectivityChangeListener {
    private static final String TAG = BaseActivity.class.getSimpleName();
    public ProgressBar progressBar;
    public Utils mUtils;
    DependancyInjection deps;
    public boolean isConnected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDependancyInjection.builder().networkModule(new NetworkModule(cacheFile)).build();
        mUtils = new Utils();
        if(savedInstanceState != null){
            ConnectionBuddyCache.clearLastNetworkState(this);
        }
        isConnected = ConnectionBuddy.getInstance().hasNetworkConnection();

    }

    public DependancyInjection getDeps() {
        return deps;
    }

    protected  void setProgressBar(){
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        mUtils.logMe(TAG + " onFailure", appErrorMessage);
    }

    @Override
    public void showToast(String Message) {
        mUtils.toastMe(BaseActivity.this, Message);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ConnectionBuddy.getInstance().registerForConnectivityEvents(this, this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ConnectionBuddy.getInstance().unregisterFromConnectivityEvents(this);
    }

    @Override
    public void onConnectionChange(ConnectivityEvent event) {
        if(event.getState() == ConnectivityState.CONNECTED){
            // device has active internet connection
            isConnected = true;
        }
        else{
            isConnected =false;
            // there is no active internet connection on this device
        }
    }
}
