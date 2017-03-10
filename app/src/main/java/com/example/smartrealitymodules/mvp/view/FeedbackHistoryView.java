package com.example.smartrealitymodules.mvp.view;

import com.example.smartrealitymodules.models.response.DisplayFeedbackRes;

/**
 * Created by user on 9/3/17.
 */
public interface FeedbackHistoryView extends BaseView{
    void getDisplayFeedBack(DisplayFeedbackRes displayFeedbackRes);

    void showEmptyView();
}
