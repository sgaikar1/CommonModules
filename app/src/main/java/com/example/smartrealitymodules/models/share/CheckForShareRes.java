package com.example.smartrealitymodules.models.share;

import java.util.ArrayList;

/**
 * Created by user on 23/2/17.
 */
public class CheckForShareRes {
    String status;
    ArrayList<result> result;

    public CheckForShareRes(String status, ArrayList<CheckForShareRes.result> result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<CheckForShareRes.result> getResult() {
        return result;
    }

    public void setResult(ArrayList<CheckForShareRes.result> result) {
        this.result = result;
    }

    public class result {
        String MobileNo, Remark;

        public result(String mobileNo, String remark) {
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
}