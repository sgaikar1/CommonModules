package com.example.smartrealitymodules.ui.BaseActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.deps.DaggerDeps;
import com.example.smartrealitymodules.deps.Deps;
import com.example.smartrealitymodules.api.NetworkModule;
import com.example.smartrealitymodules.mvp.view.BaseView;
import com.example.smartrealitymodules.utils.Utils;

import java.io.File;

/**
 * Created by ennur on 6/28/16.
 */
public class BaseActivity extends AppCompatActivity implements BaseView{
    private static final String TAG = BaseActivity.class.getSimpleName();
    Deps deps;
    private ProgressBar progressBar;
    Utils mUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
        mUtils = new Utils();
    }

    public Deps getDeps() {
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
        mUtils.logMe(TAG+" onFailure",appErrorMessage);
    }

    @Override
    public void showToast(String Message) {
        mUtils.toastMe(BaseActivity.this,Message);
    }

}
