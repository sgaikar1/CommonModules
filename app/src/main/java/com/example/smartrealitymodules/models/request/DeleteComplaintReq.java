package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 10/3/17.
 */
public class DeleteComplaintReq {

    String  sProjectCode, sUserType, sUserID, sFacilityID;

    public DeleteComplaintReq(String sProjectCode, String sUserType, String sUserID, String sFacilityID) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sFacilityID = sFacilityID;
    }
}