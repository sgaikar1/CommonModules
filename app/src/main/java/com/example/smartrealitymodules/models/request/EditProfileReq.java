package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 8/3/17.
 */

import com.google.gson.annotations.SerializedName;


public class EditProfileReq {

    @SerializedName("sProjectCode")
    public String sProjectCode;
    @SerializedName("sUserType")
    public String sUserType;
    @SerializedName("sUserID")
    public String sUserID;

    @SerializedName("sName")
    public String sName;
    @SerializedName("sMobileNo")
    public String sMobileNo;
    @SerializedName("sAltMobNo")
    public String sAltMobNo;
    @SerializedName("sEmailID")
    public String sEmailID;
    @SerializedName("sDOB")
    public String sDOB;
    @SerializedName("sAnniversary")
    public String sAnniversary;
    @SerializedName("sAddress")
    public String sAddress;
    @SerializedName("sPinCode")
    public String sPinCode;
    @SerializedName("sMaritalStatus")
    public String sMaritalStatus;
    @SerializedName("sProfilePhoto")
    public String sProfilePhoto;

    public EditProfileReq(String sProjectCode, String sUserType, String sUserID, String sName, String sMobileNo, String sAltMobNo, String semailID, String sDOB, String sAnniversary, String sAddress, String sPinCode, String sMaritalStatus, String sProfilePhoto) {

        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sName = sName;
        this.sMobileNo = sMobileNo;
        this.sAltMobNo = sAltMobNo;
        this.sEmailID = semailID;
        this.sDOB = sDOB;
        this.sAnniversary = sAnniversary;
        this.sAddress = sAddress;
        this.sPinCode = sPinCode;
        this.sMaritalStatus = sMaritalStatus;
        this.sProfilePhoto = sProfilePhoto;
    }

}
