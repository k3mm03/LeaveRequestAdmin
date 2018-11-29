package com.newwavetech.leaverequestadmindemo1.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DecisionList {

    @SerializedName("decision")
    List<Decision> decisionList;

    public DecisionList(List<Decision> decisionList) {

        this.decisionList = decisionList;
    }

    public List<Decision> getDecisionList() {
        return decisionList;
    }

    public void setDecisionList(List<Decision> decisionList) {
        this.decisionList = decisionList;
    }
}
