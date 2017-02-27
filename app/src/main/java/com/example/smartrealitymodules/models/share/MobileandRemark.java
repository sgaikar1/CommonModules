package com.example.smartrealitymodules.models.share;

public class MobileandRemark {
    String MobileNo = "";
    String Remark = "";

    public MobileandRemark(String mobileNo, String remark) {
        super();
        MobileNo = mobileNo;
        Remark = remark;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

}
