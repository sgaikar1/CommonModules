package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 1/3/17.
 */
public class ScheduleSiteVisitReq {

    String sProjectCode, sUserType, sUserID, sProjectID, sScheduleType, sScheduleDateTime, sIsHomePick, sPickupTime, sAddress, sCity;

    public ScheduleSiteVisitReq(String sProjectCode, String sUserType, String sUserID, String sProjectID, String sScheduleType, String sScheduleDateTime, String sIsHomePick, String sPickupTime, String sAddress, String sCity) {
        this.sProjectCode = sProjectCode;
        this.sUserType = sUserType;
        this.sUserID = sUserID;
        this.sProjectID = sProjectID;
        this.sScheduleType = sScheduleType;
        this.sScheduleDateTime = sScheduleDateTime;
        this.sIsHomePick = sIsHomePick;
        this.sPickupTime = sPickupTime;
        this.sAddress = sAddress;
        this.sCity = sCity;
    }
}
