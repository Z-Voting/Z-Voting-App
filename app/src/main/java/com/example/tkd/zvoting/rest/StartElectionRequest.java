package com.example.tkd.zvoting.rest;

public class StartElectionRequest {
    String electionId;

    public StartElectionRequest() {
    }

    public StartElectionRequest(String electionId) {
        this.electionId = electionId;
    }

    public String getElectionId() {
        return electionId;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }
}
