package com.example.smartrealitymodules.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.smartrealitymodules.api.NetworkModule;
import com.example.smartrealitymodules.dependancy.DaggerDependancyInjection;
import com.example.smartrealitymodules.dependancy.DependancyInjection;
import com.example.smartrealitymodules.mvp.view.BaseView;
import com.example.smartrealitymodules.utils.Utils;

import java.io.File;

/**
 * Created by user on 9/3/17.
 */
public class BaseFragment extends Fragment implements BaseView{

    private DependancyInjection deps;
    private ProgressDialog pDialog;
    public Utils mUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getActivity().getCacheDir(), "responses");
        deps = DaggerDependancyInjection.builder().networkModule(new NetworkModule(cacheFile)).build();

        mUtils = new Utils();
        pDialog = new ProgressDialog(getContext());
    }

    public DependancyInjection getDeps() {
        return deps;
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void showToast(String Message) {
        new Utils().toastMe(getActivity(), Message);
    }

    @Override
    public void showProgressDialog() {
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void showCancelableProgressDialog() {

        pDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        pDialog.dismiss();
    }
}
