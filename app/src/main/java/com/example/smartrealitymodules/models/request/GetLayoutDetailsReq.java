package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 2/3/17.
 */

public class GetLayoutDetailsReq {
    String  sProjectCode, sUserType, sUserID, sProjectID;

    public GetLayoutDetailsReq(String sProjectCode, String sUserType, String sUserID, String sProjectID) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sProjectID = sProjectID;
    }
}
