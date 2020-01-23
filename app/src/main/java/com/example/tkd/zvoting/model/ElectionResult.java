package com.example.tkd.zvoting.model;

public class ElectionResult {
    String id;
    String publisherID;
    String electionID;
    String doctype;

    long[] values;

    public ElectionResult() {
    }

    public ElectionResult(String id, String publisherID, String electionID, String doctype, long[] values) {
        this.id = id;
        this.publisherID = publisherID;
        this.electionID = electionID;
        this.doctype = doctype;
        this.values = values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
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

    public long[] getValues() {
        return values;
    }

    public void setValues(long[] values) {
        this.values = values;
    }
}
