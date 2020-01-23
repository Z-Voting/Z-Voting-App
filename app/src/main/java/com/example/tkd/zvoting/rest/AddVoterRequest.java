package com.example.tkd.zvoting.rest;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddVoterRequest implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("v1")
    @Expose
    private String v1;
    @SerializedName("v2")
    @Expose
    private String v2;
    @SerializedName("v3")
    @Expose
    private String v3;
    @SerializedName("electionId")
    @Expose
    private String electionId;
    private final static long serialVersionUID = 7733090181673195717L;

    /**
     * No args constructor for use in serialization
     *
     */
    public AddVoterRequest() {
    }

    /**
     *
     * @param electionId
     * @param name
     * @param v1
     * @param v2
     * @param v3
     * @param email
     */
    public AddVoterRequest(String name, String email, String v1, String v2, String v3, String electionId) {
        super();
        this.name = name;
        this.email = email;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.electionId = electionId;
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

    public String getElectionId() {
        return electionId;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("email", email).append("v1", v1).append("v2", v2).append("v3", v3).append("electionId", electionId).toString();
    }

}