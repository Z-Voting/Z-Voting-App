package com.example.tkd.zvoting.rest;

public class CalculateResultRequest {
    String electionId;

    public CalculateResultRequest() {
    }

    public CalculateResultRequest(String electionId) {
        this.electionId = electionId;
    }

    public String getElectionId() {
        return electionId;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }
}
