package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Employees implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;
    @SerializedName("salary")
    private int salary;
    @SerializedName("roleid")
    private int roleid;
    @SerializedName("departmentid")
    private int departmentid;
    @SerializedName("remainingleave")
    private int remainingleave;
    @SerializedName("startworkingdate")
    private String startworkingdate;
    @SerializedName("subtitle")
    private String departmenttitle;
    @SerializedName("title")
    private String roletitle;

    public Employees(String code, String name, String password, int salary, int roleid, int departmentid, int remainingleave, String startworkingdate) {
        this.code = code;
        this.name = name;
        this.password = password;
        this.salary = salary;
        this.roleid = roleid;
        this.departmentid = departmentid;
        this.remainingleave = remainingleave;
        this.startworkingdate = startworkingdate;
    }

    public Employees(int id, String code, String name, String password, int salary, int roleid, int departmentid, int remainingleave, String startworkingdate, String departmenttitle, String roletitle) {

        this.id = id;
        this.code = code;
        this.name = name;
        this.password = password;
        this.salary = salary;
        this.roleid = roleid;
        this.departmentid = departmentid;
        this.remainingleave = remainingleave;
        this.startworkingdate = startworkingdate;
        this.departmenttitle = departmenttitle;
        this.roletitle = roletitle;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public int getRemainingleave() {
        return remainingleave;
    }

    public void setRemainingleave(int remainingleave) {
        this.remainingleave = remainingleave;
    }

    public String getStartworkingdate() {
        return startworkingdate;
    }

    public void setStartworkingdate(String startworkingdate) {
        this.startworkingdate = startworkingdate;
    }

    public String getDepartmenttitle() {
        return departmenttitle;
    }

    public void setDepartmenttitle(String departmenttitle) {
        this.departmenttitle = departmenttitle;
    }

    public String getRoletitle() {
        return roletitle;
    }

    public void setRoletitle(String roletitle) {
        this.roletitle = roletitle;
    }
}
