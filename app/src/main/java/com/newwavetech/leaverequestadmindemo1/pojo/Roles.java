package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

public class Roles {

    @SerializedName("id")
    private int roleId;
    @SerializedName("title")
    private String roleTitle;
    @SerializedName("description")
    private String roleDescription;

    public Roles(int roleId, String roleTitle, String roleDescription) {
        this.roleId = roleId;
        this.roleTitle = roleTitle;
        this.roleDescription = roleDescription;
    }

    public Roles(String roleTitle, String roleDescription) {
        this.roleTitle = roleTitle;
        this.roleDescription = roleDescription;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
