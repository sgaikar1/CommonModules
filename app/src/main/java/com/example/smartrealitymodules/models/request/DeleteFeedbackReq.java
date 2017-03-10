package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 9/3/17.
 */

public class DeleteFeedbackReq {

    String  sProjectCode, sUserType, sUserID, sFeedBackID;

    public DeleteFeedbackReq(String sProjectCode, String sUserType, String sUserID, String sFeedBackID) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sFeedBackID = sFeedBackID;
    }
}
