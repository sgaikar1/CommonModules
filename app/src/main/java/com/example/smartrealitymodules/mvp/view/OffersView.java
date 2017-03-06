package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.response.GetOffersRes;

/**
 * Created by ennur on 6/25/16.
 */
public interface OffersView extends BaseView {

    void getOffersListSuccess(GetOffersRes getOffersRes);

    void dissmissDialog();
}
