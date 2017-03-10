package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 10/3/17.
 */
public class InsertUpdateComplaintRequestReq {

    String  sProjectCode, sUserType, sUserID, sFacilityID, sTitle, sDescription, sImgBase64Form;

    public InsertUpdateComplaintRequestReq(String sProjectCode, String sUserType, String sUserID, String sFacilityID, String sTitle, String sDescription, String sImgBase64Form) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sFacilityID = sFacilityID;
        this.sTitle = sTitle;
        this.sDescription = sDescription;
        this.sImgBase64Form = sImgBase64Form;
    }
}

