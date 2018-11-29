package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeList {

    @SerializedName("employee")
    private List<Employees> employeesList;

    public EmployeeList(List<Employees> employeesList) {

        this.employeesList = employeesList;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }
}
