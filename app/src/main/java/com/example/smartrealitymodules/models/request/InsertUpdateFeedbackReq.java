package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 9/3/17.
 */
public class InsertUpdateFeedbackReq {

    String  sProjectCode, sUserType, sUserID, sFeedbackID, sTypeID, sTitle, sDescription;

    public InsertUpdateFeedbackReq(String sProjectCode, String sUserType, String sUserID, String sFeedbackID, String sTypeID, String sTitle, String sDescription) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sFeedbackID = sFeedbackID;
        this.sTypeID = sTypeID;
        this.sTitle = sTitle;
        this.sDescription = sDescription;
    }

    public String getsProjectCode() {
        return sProjectCode;
    }

    public void setsProjectCode(String sProjectCode) {
        this.sProjectCode = sProjectCode;
    }

    public String getsUserType() {
        return sUserType;
    }

    public void setsUserType(String sUserType) {
        this.sUserType = sUserType;
    }

    public String getsUserID() {
        return sUserID;
    }

    public void setsUserID(String sUserID) {
        this.sUserID = sUserID;
    }

    public String getsFeedbackID() {
        return sFeedbackID;
    }

    public void setsFeedbackID(String sFeedbackID) {
        this.sFeedbackID = sFeedbackID;
    }

    public String getsTypeID() {
        return sTypeID;
    }

    public void setsTypeID(String sTypeID) {
        this.sTypeID = sTypeID;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsDescription() {
        return sDescription;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }
}
