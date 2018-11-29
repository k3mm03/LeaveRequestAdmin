package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

public class LeaveTypes {

    @SerializedName("id")
    private int leaveTypesId;
    @SerializedName("title")
    private String leaveTypesName;
    @SerializedName("description")
    private String leaveTypesDescription;

    public LeaveTypes(int leaveTypesId, String leaveTypesName, String leaveTypesDescription) {

        this.leaveTypesId = leaveTypesId;
        this.leaveTypesName = leaveTypesName;
        this.leaveTypesDescription = leaveTypesDescription;
    }

    public LeaveTypes(String leaveTypesName, String leaveTypesDescription) {
        this.leaveTypesName = leaveTypesName;
        this.leaveTypesDescription = leaveTypesDescription;
    }

    public int getLeaveTypesId() {
        return leaveTypesId;
    }

    public void setLeaveTypesId(int leaveTypesId) {
        this.leaveTypesId = leaveTypesId;
    }

    public String getLeaveTypesName() {
        return leaveTypesName;
    }

    public void setLeaveTypesName(String leaveTypesName) {
        this.leaveTypesName = leaveTypesName;
    }

    public String getLeaveTypesDescription() {
        return leaveTypesDescription;
    }

    public void setLeaveTypesDescription(String leaveTypesDescription) {
        this.leaveTypesDescription = leaveTypesDescription;
    }
}
