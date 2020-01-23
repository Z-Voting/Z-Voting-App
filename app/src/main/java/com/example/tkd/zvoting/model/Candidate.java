package com.example.tkd.zvoting.model;

public class Candidate {
    String id;
    String name;
    String sign;
    String imgAddress;
    String electionID;
    String doctype;

    public Candidate() {
    }

    public Candidate(String id, String name, String sign, String imgAddress, String electionID, String doctype) {
        this.id = id;
        this.name = name;
        this.sign = sign;
        this.imgAddress = imgAddress;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
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
