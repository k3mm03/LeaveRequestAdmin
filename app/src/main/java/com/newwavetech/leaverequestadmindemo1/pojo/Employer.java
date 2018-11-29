package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

public class Employer {

    @SerializedName("id")
    private int adminid;
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;

    public Employer(int adminid, String name, String password) {
        this.adminid = adminid;
        this.name = name;
        this.password = password;
    }

    public int getAdminid() {

        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
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
}
