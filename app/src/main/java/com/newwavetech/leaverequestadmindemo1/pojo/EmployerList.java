package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployerList {

    @SerializedName("d")
    List<Employer> employer;

    public EmployerList(List<Employer> employer) {

        this.employer = employer;
    }

    public List<Employer> getEmployer() {
        return employer;
    }

    public void setEmployer(List<Employer> employer) {
        this.employer = employer;
    }
}
