package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 8/3/17.
 */
public class MyProfileReq {

    String sUserID, sUserType;

    public MyProfileReq(String sUserID, String sUserType) {
        this.sUserID = sUserID;
        this.sUserType = sUserType;
    }
}
