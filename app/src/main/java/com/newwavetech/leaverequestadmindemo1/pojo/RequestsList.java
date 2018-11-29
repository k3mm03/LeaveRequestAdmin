package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestsList {
    @SerializedName("requestlist")
    private List<Requests> requestlist;

    public RequestsList(List<Requests> requestlist) {

        this.requestlist = requestlist;
    }

    public List<Requests> getRequestlist() {
        return requestlist;
    }

    public void setRequestlist(List<Requests> requestlist) {
        this.requestlist = requestlist;
    }
}
