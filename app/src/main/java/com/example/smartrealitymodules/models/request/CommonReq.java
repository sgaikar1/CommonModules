package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 14/2/17.
 */
public class CommonReq {

    String  sProjectCode, sUserType, sUserID;

    public CommonReq(String sProjectCode, String sUserType, String sUserID) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
    }

    public String getsUserID() {
        return sUserID;
    }

    public void setsUserID(String sUserID) {
        this.sUserID = sUserID;
    }

    public String getsUserType() {
        return sUserType;
    }

    public void setsUserType(String sUserType) {
        this.sUserType = sUserType;
    }

    public String getsProjectCode() {
        return sProjectCode;
    }

    public void setsProjectCode(String sProjectCode) {
        this.sProjectCode = sProjectCode;
    }
}
