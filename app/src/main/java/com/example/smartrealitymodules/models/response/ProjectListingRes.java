package com.example.smartrealitymodules.models.response;

/**
 * Created by user on 27/2/17.
 */

import java.util.ArrayList;

/**
 * Created by vijay on 18/11/16.
 */

public class ProjectListingRes {

    public String status;
    public String message;
    public ArrayList<Result> result = new ArrayList<Result>();

    public ProjectListingRes(String status, String message, ArrayList<Result> result) {
        this.status = status;
        this.message = message;
        this.result = result;
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

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }


    public class Result {

        public String ProjectID;
        public String ProjectName;
        public String CityName;
        public String LocalArea;
        public String ProjectType;
        public String Lat;
        public String Long;
        public String ContactNo;
        public String ApartmentType;
        public String ProjectStatus;
        public String MinBudget;
        public String MaxBudget;
        public String InterestedIn;
        public String FilePath;
        public String Location;
        public String MinBudgetSearch;

        public Result(String projectID, String projectName, String cityName, String localArea, String projectType, String lat, String aLong, String contactNo, String apartmentType, String projectStatus, String minBudget, String maxBudget, String interestedIn, String filePath, String location, String minBudgetSearch) {
            ProjectID = projectID;
            ProjectName = projectName;
            CityName = cityName;
            LocalArea = localArea;
            ProjectType = projectType;
            Lat = lat;
            Long = aLong;
            ContactNo = contactNo;
            ApartmentType = apartmentType;
            ProjectStatus = projectStatus;
            MinBudget = minBudget;
            MaxBudget = maxBudget;
            InterestedIn = interestedIn;
            FilePath = filePath;
            Location = location;
            MinBudgetSearch = minBudgetSearch;
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

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String cityName) {
            CityName = cityName;
        }

        public String getLocalArea() {
            return LocalArea;
        }

        public void setLocalArea(String localArea) {
            LocalArea = localArea;
        }

        public String getProjectType() {
            return ProjectType;
        }

        public void setProjectType(String projectType) {
            ProjectType = projectType;
        }

        public String getLat() {
            return Lat;
        }

        public void setLat(String lat) {
            Lat = lat;
        }

        public String getLong() {
            return Long;
        }

        public void setLong(String aLong) {
            Long = aLong;
        }

        public String getContactNo() {
            return ContactNo;
        }

        public void setContactNo(String contactNo) {
            ContactNo = contactNo;
        }

        public String getApartmentType() {
            return ApartmentType;
        }

        public void setApartmentType(String apartmentType) {
            ApartmentType = apartmentType;
        }

        public String getProjectStatus() {
            return ProjectStatus;
        }

        public void setProjectStatus(String projectStatus) {
            ProjectStatus = projectStatus;
        }

        public String getMinBudget() {
            return MinBudget;
        }

        public void setMinBudget(String minBudget) {
            MinBudget = minBudget;
        }

        public String getMaxBudget() {
            return MaxBudget;
        }

        public void setMaxBudget(String maxBudget) {
            MaxBudget = maxBudget;
        }

        public String getInterestedIn() {
            return InterestedIn;
        }

        public void setInterestedIn(String interestedIn) {
            InterestedIn = interestedIn;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }


        public String getLocation() {
            return Location;
        }

        public void setLocation(String location) {
            Location = location;
        }

        public String getMinBudgetSearch() {
            return MinBudgetSearch;
        }

        public void setMinBudgetSearch(String minBudgetSearch) {
            MinBudgetSearch = minBudgetSearch;
        }
    }
}