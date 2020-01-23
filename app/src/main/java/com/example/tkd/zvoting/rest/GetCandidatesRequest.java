package com.example.tkd.zvoting.rest;

import java.io.Serializable;

public class GetCandidatesRequest implements Serializable {
    String electionId;

    public GetCandidatesRequest() {
    }

    public GetCandidatesRequest(String electionId) {
        this.electionId = electionId;
    }

    public String getElectionId() {
        return electionId;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }
}
