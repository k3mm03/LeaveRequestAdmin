package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

public class Departments {

    @SerializedName("id")
    private int departmentId;
    @SerializedName("subtitle")
    private String departmentTitle;
    @SerializedName("description")
    private String departmentDescription;

    public Departments(int departmentId, String departmentTitle, String departmentDescription) {
        this.departmentId = departmentId;
        this.departmentTitle = departmentTitle;
        this.departmentDescription = departmentDescription;
    }

    public Departments(String departmentTitle, String departmentDescription) {
        this.departmentTitle = departmentTitle;
        this.departmentDescription = departmentDescription;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }
}
