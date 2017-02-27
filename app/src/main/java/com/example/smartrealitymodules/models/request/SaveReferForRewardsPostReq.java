package com.example.smartrealitymodules.models.request;

import java.util.ArrayList;

/**
 * Created by vijay on 7/12/16.
 */

public class SaveReferForRewardsPostReq {
    String sProjectCode, sUserType, sUserID;
    ArrayList<ReferForReward> sReferForRewards = new ArrayList<ReferForReward>();

    public SaveReferForRewardsPostReq(String sProjectCode, String sUserType, String sUserID, ArrayList<ReferForReward> referForRewards) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sReferForRewards = referForRewards;
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

    public ArrayList<ReferForReward> getReferForRewards() {
        return sReferForRewards;
    }

    public void setReferForRewards(ArrayList<ReferForReward> referForRewards) {
        sReferForRewards = referForRewards;
    }

    public static class ReferForReward {

        public String sName;
        public String sMobileNo;
        public String sAltMobileNo;
        public String sEmailID;
        public String sProjectID;
        public String sDateOfBirth;
        public String sAddress;
        public String sPincode;

        public ReferForReward(String sName, String sMobileNo, String sAltMobileNo, String sEmailID, String sProjectID, String sDateOfBirth, String sAddress, String sPincode) {
            this.sName = sName;
            this.sMobileNo = sMobileNo;
            this.sAltMobileNo = sAltMobileNo;
            this.sEmailID = sEmailID;
            this.sProjectID = sProjectID;
            this.sDateOfBirth = sDateOfBirth;
            this.sAddress = sAddress;
            this.sPincode = sPincode;
        }

        public String getsName() {
            return sName;
        }

        public void setsName(String sName) {
            this.sName = sName;
        }

        public String getsMobileNo() {
            return sMobileNo;
        }

        public void setsMobileNo(String sMobileNo) {
            this.sMobileNo = sMobileNo;
        }

        public String getsAltMobileNo() {
            return sAltMobileNo;
        }

        public void setsAltMobileNo(String sAltMobileNo) {
            this.sAltMobileNo = sAltMobileNo;
        }

        public String getsEmailID() {
            return sEmailID;
        }

        public void setsEmailID(String sEmailID) {
            this.sEmailID = sEmailID;
        }

        public String getsProjectID() {
            return sProjectID;
        }

        public void setsProjectID(String sProjectID) {
            this.sProjectID = sProjectID;
        }

        public String getsDateOfBirth() {
            return sDateOfBirth;
        }

        public void setsDateOfBirth(String sDateOfBirth) {
            this.sDateOfBirth = sDateOfBirth;
        }

        public String getsAddress() {
            return sAddress;
        }

        public void setsAddress(String sAddress) {
            this.sAddress = sAddress;
        }

        public String getsPincode() {
            return sPincode;
        }

        public void setsPincode(String sPincode) {
            this.sPincode = sPincode;
        }
    }

}
