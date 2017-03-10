package com.example.smartrealitymodules.models.response;

import java.util.ArrayList;

/**
 * Created by user on 9/3/17.
 */
public class DisplayFeedbackRes {
    public String status;
    public String message;
    public ArrayList<Result> result = new ArrayList<Result>();
    public ArrayList<FeedbackTypes> FeedbackType = new ArrayList<FeedbackTypes>();

    public DisplayFeedbackRes(String status, String message, ArrayList<Result> result, ArrayList<FeedbackTypes> feedbackType) {
        this.status = status;
        this.message = message;
        this.result = result;
        FeedbackType = feedbackType;
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

    public ArrayList<FeedbackTypes> getFeedbackType() {
        return FeedbackType;
    }

    public void setFeedbackType(ArrayList<FeedbackTypes> feedbackType) {
        FeedbackType = feedbackType;
    }

    public class Result {

        public String FeedbackID;
        public String Type;
        public String Date;
        public String Title;
        public String Description;

        public Result(String feedbackID, String type, String date, String title, String description) {
            FeedbackID = feedbackID;
            Type = type;
            Date = date;
            Title = title;
            Description = description;
        }

        public String getFeedbackID() {
            return FeedbackID;
        }

        public void setFeedbackID(String feedbackID) {
            FeedbackID = feedbackID;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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
    }

    public class FeedbackTypes {

        public String TypeID;
        public String F_Type;

        public FeedbackTypes(String typeID, String f_Type) {
            TypeID = typeID;
            F_Type = f_Type;
        }

        public String getTypeID() {
            return TypeID;
        }

        public void setTypeID(String typeID) {
            TypeID = typeID;
        }

        public String getF_Type() {
            return F_Type;
        }

        public void setF_Type(String f_Type) {
            F_Type = f_Type;
        }
    }

}
