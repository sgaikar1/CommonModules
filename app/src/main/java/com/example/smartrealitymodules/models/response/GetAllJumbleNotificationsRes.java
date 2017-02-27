package com.example.smartrealitymodules.models.response;

import java.util.ArrayList;


public class GetAllJumbleNotificationsRes {
    public ArrayList<Result> result = new ArrayList<Result>();
    public String status;
    public String message;

    public GetAllJumbleNotificationsRes(String status, String message, ArrayList<Result> result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
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

    public class Result {
        public String NotificationID;
        public String ProjectID;
        public String NotificationType;
        public String Message;
        public String MessageDate;
        public String Title;
        public String ProjectImage;
        public String IsCounterRead;

        public Result(String notificationID, String projectID, String notificationType, String message, String messageDate, String title, String projectImage, String isCounterRead) {
            NotificationID = notificationID;
            ProjectID = projectID;
            NotificationType = notificationType;
            Message = message;
            MessageDate = messageDate;
            Title = title;
            ProjectImage = projectImage;
            IsCounterRead = isCounterRead;
        }


        public String getProjectID() {
            return ProjectID;
        }

        public void setProjectID(String projectID) {
            ProjectID = projectID;
        }

        public String getNotificationID() {
            return NotificationID;
        }

        public void setNotificationID(String notificationID) {
            NotificationID = notificationID;
        }

        public String getIsCounterRead() {
            return IsCounterRead;
        }

        public void setIsCounterRead(String isCounterRead) {
            IsCounterRead = isCounterRead;
        }

        public String getNotificationType() {
            return NotificationType;
        }

        public void setNotificationType(String notificationType) {
            NotificationType = notificationType;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            Message = message;
        }

        public String getMessageDate() {
            return MessageDate;
        }

        public void setMessageDate(String messageDate) {
            MessageDate = messageDate;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getProjectImage() {
            return ProjectImage;
        }

        public void setProjectImage(String projectImage) {
            ProjectImage = projectImage;
        }
    }

}
