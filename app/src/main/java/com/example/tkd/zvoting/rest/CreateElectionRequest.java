package com.example.tkd.zvoting.rest;

public class CreateElectionRequest {

    String electionName;
    String electionDuration;

    public CreateElectionRequest() {
    }

    public CreateElectionRequest(String electionName, String electionDuration) {
        this.electionName = electionName;
        this.electionDuration = electionDuration;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getElectionDuration() {
        return electionDuration;
    }

    public void setElectionDuration(String electionDuration) {
        this.electionDuration = electionDuration;
    }
}
