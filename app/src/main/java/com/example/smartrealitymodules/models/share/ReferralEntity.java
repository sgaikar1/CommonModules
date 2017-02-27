package com.example.smartrealitymodules.models.share;

public class ReferralEntity {
    String strEmailID, strMobile;

    public ReferralEntity(String strEmailID, String strMobile) {
        this.strEmailID = strEmailID;
        this.strMobile = strMobile;
    }

    public String getStrEmailID() {
        return strEmailID;
    }

    public void setStrEmailID(String strEmailID) {
        this.strEmailID = strEmailID;
    }

    public String getStrMobile() {
        return strMobile;
    }

    public void setStrMobile(String strMobile) {
        this.strMobile = strMobile;
    }
}
