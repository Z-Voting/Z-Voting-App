package com.example.tkd.zvoting.model;

public class Voter {
    String id;
    String name;
    String email;
    String v1;
    String v2;
    String v3;
    String electionID;
    String doctype;

    public Voter() {
    }

    public Voter(String id, String name, String email, String v1, String v2, String v3, String electionID, String doctype) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.electionID = electionID;
        this.doctype = doctype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
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
