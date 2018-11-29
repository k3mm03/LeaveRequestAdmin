package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Decision implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("requestid")
    private int requestid;
    @SerializedName("employeeid")
    private int employeeid;
    @SerializedName("adminid")
    private int adminid;
    @SerializedName("approve")
    private int approve;
    @SerializedName("leavetypeid")
    private int leavetypeid;
    @SerializedName("remark")
    private String remark;
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
    @SerializedName("helpingempid")
    private int helpingempid;
    @SerializedName("explained")
    private int explained;
    @SerializedName("assigntask")
    private String assigntask;
    @SerializedName("name")
    private String name;
    @SerializedName("title")
    private String title;
    @SerializedName("adminname")
    private String adminname;
    @SerializedName("decisiontime")
    private String decisiontime;

    public String getDecisiontime() {
        return decisiontime;
    }

    public void setDecisiontime(String decisiontime) {
        this.decisiontime = decisiontime;
    }

    public Decision(int id, int requestid, int employeeid, int adminid, int approve, int leavetypeid, String remark, int totaldays, String startdate, String enddate, String reason, String determine, int helpingempid, int explained, String assigntask, String name, String title, String adminname, String decisiontime) {
        this.id = id;
        this.requestid = requestid;
        this.employeeid = employeeid;
        this.adminid = adminid;
        this.approve = approve;
        this.leavetypeid = leavetypeid;
        this.remark = remark;
        this.totaldays = totaldays;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reason = reason;
        this.determine = determine;
        this.helpingempid = helpingempid;
        this.explained = explained;
        this.assigntask = assigntask;
        this.name = name;
        this.title = title;
        this.adminname = adminname;
        this.decisiontime = decisiontime;

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

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public int getLeavetypeid() {
        return leavetypeid;
    }

    public void setLeavetypeid(int leavetypeid) {
        this.leavetypeid = leavetypeid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public int getHelpingempid() {
        return helpingempid;
    }

    public void setHelpingempid(int helpingempid) {
        this.helpingempid = helpingempid;
    }

    public int getExplained() {
        return explained;
    }

    public void setExplained(int explained) {
        this.explained = explained;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }
}
