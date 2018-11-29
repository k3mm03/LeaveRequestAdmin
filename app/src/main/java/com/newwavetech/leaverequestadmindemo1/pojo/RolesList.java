package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RolesList {
    @SerializedName("role")
    List<Roles> rolesList;

    public RolesList(List<Roles> rolesList) {

        this.rolesList = rolesList;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }
}
