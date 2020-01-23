package com.example.tkd.zvoting.model;

public class Vote {
    String id;
    String voterID;
    String values;
    String electionID;
    String doctype;

    public Vote() {
    }

    public Vote(String id, String voterID, String values, String electionID, String doctype) {
        this.id = id;
        this.voterID = voterID;
        this.values = values;
        this.electionID = electionID;
        this.doctype = doctype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoterID() {
        return voterID;
    }

    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getElectionID() {
        return electionID;
    }

    public void setElectionID(String electionID) {
        this.electionID = electionID;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }
}
