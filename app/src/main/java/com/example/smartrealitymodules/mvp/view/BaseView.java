package com.example.smartrealitymodules.mvp.view;

/**
 * Created by user on 13/2/17.
 */
public interface BaseView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void showToast(String Message);

    void showProgressDialog();

    void showCancelableProgressDialog();

    void hideProgressDialog();
}
