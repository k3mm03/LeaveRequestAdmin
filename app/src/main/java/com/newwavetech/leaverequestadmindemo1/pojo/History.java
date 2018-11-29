package com.newwavetech.leaverequestadmindemo1.pojo;

public class History {

    private String name;
    private String role;
    private String leavesDay;
    private String startDate;
    private String endDate;
    private String reason;
    private boolean decision;

    public History(String name, String role, String leavesDay, String startDate, String endDate, String reason, boolean decision) {
        this.name = name;
        this.role = role;
        this.leavesDay = leavesDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.decision = decision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLeavesDay() {
        return leavesDay;
    }

    public void setLeavesDay(String leavesDay) {
        this.leavesDay = leavesDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
