package com.example.smartrealitymodules.models.response;

import java.util.ArrayList;

/**
 * Created by user on 10/3/17.
 */
public class GetComplaintsHistoryRes {

    public String status;
    public String message;
    public ArrayList<Facilitys> Facility = new ArrayList<Facilitys>();

    public GetComplaintsHistoryRes(String status, String message, ArrayList<Facilitys> facility) {
        this.status = status;
        this.message = message;
        Facility = facility;
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

    public ArrayList<Facilitys> getFacility() {
        return Facility;
    }

    public void setFacility(ArrayList<Facilitys> facility) {
        Facility = facility;
    }

    public class Facilitys {

        public String FacilityID;
        public String Title;
        public String Description;
        public String ImagePath;
        public String FacilityStatus;

        public Facilitys(String facilityID, String title, String description, String imagePath, String facilityStatus) {
            FacilityID = facilityID;
            Title = title;
            Description = description;
            ImagePath = imagePath;
            FacilityStatus = facilityStatus;
        }

        public String getFacilityID() {
            return FacilityID;
        }

        public void setFacilityID(String facilityID) {
            FacilityID = facilityID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String imagePath) {
            ImagePath = imagePath;
        }

        public String getFacilityStatus() {
            return FacilityStatus;
        }

        public void setFacilityStatus(String facilityStatus) {
            FacilityStatus = facilityStatus;
        }
    }

}
