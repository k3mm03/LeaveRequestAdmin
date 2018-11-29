package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaveTypesList {

    @SerializedName("leaves")
    List<LeaveTypes> leaveTypesList;

    public LeaveTypesList(List<LeaveTypes> leaveTypesList) {

        this.leaveTypesList = leaveTypesList;
    }

    public List<LeaveTypes> getLeaveTypesList() {
        return leaveTypesList;
    }

    public void setLeaveTypesList(List<LeaveTypes> leaveTypesList) {
        this.leaveTypesList = leaveTypesList;
    }
}
