package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Requests implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("employeeid")
    private int employeeid;
    @SerializedName("name")
    private String name;
    @SerializedName("totaldays")
    private int totaldays;
    @SerializedName("startdate")
    private String startdate;
    @SerializedName("enddate")
    private String enddate;
    @SerializedName("reason")
    private String reason;
    @SerializedName("determine")
    private String determine;
    @SerializedName("helpername")
    private String helpername;
    @SerializedName("explained")
    private int explained;
    @SerializedName("assigntask")
    private String assigntask;
    public Requests(int id, int employeeid, String name, int totaldays, String startdate, String enddate, String reason, String determine, String helpername, int explained, String assigntask) {
        this.id = id;
        this.employeeid = employeeid;
        this.name = name;
        this.totaldays = totaldays;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reason = reason;
        this.determine = determine;
        this.helpername = helpername;
        this.explained = explained;
        this.assigntask = assigntask;
    }
    public Requests() {
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getAssigntask() {

        return assigntask;
    }

    public void setAssigntask(String assigntask) {
        this.assigntask = assigntask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotaldays() {
        return totaldays;
    }

    public void setTotaldays(int totaldays) {
        this.totaldays = totaldays;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDetermine() {
        return determine;
    }

    public void setDetermine(String determine) {
        this.determine = determine;
    }

    public String getHelpername() {
        return helpername;
    }

    public void setHelpername(String helpername) {
        this.helpername = helpername;
    }

    public int getExplained() {
        return explained;
    }

    public void setExplained(int explained) {
        this.explained = explained;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
