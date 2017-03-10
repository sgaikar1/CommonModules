package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.response.GetComplaintsHistoryRes;

/**
 * Created by user on 10/3/17.
 */
public interface ComplaintsHistoryView extends BaseView{
    void getDisplayComplaints(GetComplaintsHistoryRes displayFeedbackRes);

    void showEmptyView();
}
