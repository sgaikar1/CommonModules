package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 10/3/17.
 */

public class SendMISCountReq {

    public String sProjectCode;
    public String sUserType;
    public String sUserID;
    public String sMobileNo;
    public String sType;
    public String sUniqueDeviceID;
    public String sDevice;
    public String sEmailID;

    public SendMISCountReq(String sProjectCode, String sUserType, String sUserID, String sMobileNo, String sType, String sUniqueDeviceID, String sDevice, String sEmailID) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sMobileNo = sMobileNo;
        this.sType = sType;
        this.sUniqueDeviceID = sUniqueDeviceID;
        this.sDevice = sDevice;
        this.sEmailID = sEmailID;
    }
}
