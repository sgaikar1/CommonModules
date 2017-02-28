package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 27/2/17.
 */
public class ProjectDetailsReq {
    String sProjectCode, sUserType, sUserID, sProjectID;

    public ProjectDetailsReq(String sProjectCode, String sUserType, String sUserID, String sProjectID) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sProjectID = sProjectID;
    }
}