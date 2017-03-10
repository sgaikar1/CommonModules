package com.example.smartrealitymodules.models.response;

import java.util.ArrayList;

/**
 * Created by user on 9/3/17.
 */
public class VerifyMobileNumberRes {
    private VerifyMobileNumberResult result;
    private  ArrayList<VerifyMobileNumberProjects> Project;
    private  String status;
    private  String message;

    public VerifyMobileNumberRes(String status, String message, VerifyMobileNumberResult result, ArrayList<VerifyMobileNumberProjects> project) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.Project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VerifyMobileNumberResult getResult() {
        return result;
    }

    public void setResult(VerifyMobileNumberResult result) {
        this.result = result;
    }

    public ArrayList<VerifyMobileNumberProjects> getProject() {
        return Project;
    }

    public void setProject(ArrayList<VerifyMobileNumberProjects> project) {
        Project = project;
    }

    public class VerifyMobileNumberResult {

        public String CustomerID, Name, EmailID, MobileNo, AltMobileNo, DOB, MaritalStatus, OTP;

        public VerifyMobileNumberResult(String customerID, String name, String emailID, String mobileNo, String altMobileNo, String DOB, String maritalStatus, String OTP) {
            this.CustomerID = customerID;
            this.Name = name;
            this.EmailID = emailID;
            this.MobileNo = mobileNo;
            this.AltMobileNo = altMobileNo;
            this.DOB = DOB;
            this.MaritalStatus = maritalStatus;
            this.OTP = OTP;
        }

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String customerID) {
            CustomerID = customerID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getEmailID() {
            return EmailID;
        }

        public void setEmailID(String emailID) {
            EmailID = emailID;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String mobileNo) {
            MobileNo = mobileNo;
        }

        public String getAltMobileNo() {
            return AltMobileNo;
        }

        public void setAltMobileNo(String altMobileNo) {
            AltMobileNo = altMobileNo;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getMaritalStatus() {
            return MaritalStatus;
        }

        public void setMaritalStatus(String maritalStatus) {
            MaritalStatus = maritalStatus;
        }

        public String getOTP() {
            return OTP;
        }

        public void setOTP(String OTP) {
            this.OTP = OTP;
        }
    }

    public class VerifyMobileNumberProjects {
        public String ProjectID, ProjectName;


        public VerifyMobileNumberProjects(String projectID, String projectName) {
            ProjectID = projectID;
            ProjectName = projectName;
        }

        public String getProjectID() {
            return ProjectID;
        }

        public void setProjectID(String projectID) {
            ProjectID = projectID;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String projectName) {
            ProjectName = projectName;
        }
    }
}
