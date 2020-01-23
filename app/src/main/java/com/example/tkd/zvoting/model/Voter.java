package com.example.tkd.zvoting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Voter implements Serializable
{

    @SerializedName("doctype")
    @Expose
    private String doctype;
    @SerializedName("electionID")
    @Expose
    private String electionID;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("v1")
    @Expose
    private String v1;
    @SerializedName("v2")
    @Expose
    private String v2;
    @SerializedName("v3")
    @Expose
    private String v3;
    private final static long serialVersionUID = -8333242822293631593L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Voter() {
    }

    /**
     *
     * @param doctype
     * @param electionID
     * @param name
     * @param id
     * @param v1
     * @param v2
     * @param v3
     * @param email
     */
    public Voter(String doctype, String electionID, String email, String id, String name, String v1, String v2, String v3) {
        super();
        this.doctype = doctype;
        this.electionID = electionID;
        this.email = email;
        this.id = id;
        this.name = name;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getElectionID() {
        return electionID;
    }

    public void setElectionID(String electionID) {
        this.electionID = electionID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("doctype", doctype).append("electionID", electionID).append("email", email).append("id", id).append("name", name).append("v1", v1).append("v2", v2).append("v3", v3).toString();
    }

}