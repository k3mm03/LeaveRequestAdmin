package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepartmentsList {

    @SerializedName("department")
    List<Departments> departmentsList;

    public DepartmentsList(List<Departments> departmentsList) {

        this.departmentsList = departmentsList;
    }

    public List<Departments> getDepartmentsList() {
        return departmentsList;
    }

    public void setDepartmentsList(List<Departments> departmentsList) {
        this.departmentsList = departmentsList;
    }
}
