package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 27/2/17.
 */
public class ProjectInterestedInReq {
    String sProjectCode, sUserType, sUserID, sProjectID, sStatus;

    public ProjectInterestedInReq(String sProjectCode, String sUserType, String sUserID, String sProjectID, String sStatus) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sProjectID = sProjectID;
        this.sStatus = sStatus;
    }
}
