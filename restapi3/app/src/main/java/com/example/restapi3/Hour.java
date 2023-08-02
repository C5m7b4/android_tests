package com.example.restapi3;

import java.util.Date;

public class Hour {
    public Hour(Integer id, String workDate, String startTime, String endTime, Float totalHours) {
        Id = id;
        WorkDate = workDate;
        StartTime = startTime;
        EndTime = endTime;
        TotalHours = totalHours;
    }

    public Hour() {

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String workDate) {
        WorkDate = workDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public Float getTotalHours() {
        return TotalHours;
    }

    public void setTotalHours(Float totalHours) {
        TotalHours = totalHours;
    }

    public void setUserid(Integer id){
        Userid = id;
    }

    public int getUserid(){
        return Userid;
    }

    @Override
    public String toString() {
        return  "Userid=" + Userid + '\'' +
                "Work Date='" + WorkDate + '\'' +
                "Start='" + StartTime + '\'' +
                "End='" + EndTime + '\'' +
                "Total Hours=" + TotalHours;
    }

    private Integer Id;
    private Integer Userid;
    private String WorkDate;
    private String StartTime;
    private String EndTime;
    private Float TotalHours;
}
