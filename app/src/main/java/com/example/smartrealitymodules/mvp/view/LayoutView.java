package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.response.GetLayoutDetailsRes;

/**
 * Created by user on 2/3/17.
 */
public interface LayoutView extends BaseView{
    void getLayoutDetailsSuccess(GetLayoutDetailsRes getLayoutDetailsRes);

    void showEmptyView();

}
