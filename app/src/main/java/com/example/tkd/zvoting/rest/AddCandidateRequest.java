package com.example.tkd.zvoting.rest;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class AddCandidateRequest implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sign")
    @Expose
    private String sign;
    @SerializedName("imgAddress")
    @Expose
    private String imgAddress;
    @SerializedName("electionId")
    @Expose
    private String electionId;
    private final static long serialVersionUID = -1785708481179338667L;

    /**
     * No args constructor for use in serialization
     *
     */
    public AddCandidateRequest() {
    }

    /**
     *
     * @param electionId
     * @param name
     * @param sign
     * @param imgAddress
     */
    public AddCandidateRequest(String name, String sign, String imgAddress, String electionId) {
        super();
        this.name = name;
        this.sign = sign;
        this.imgAddress = imgAddress;
        this.electionId = electionId;
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

    public String getElectionId() {
        return electionId;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("sign", sign).append("imgAddress", imgAddress).append("electionId", electionId).toString();
    }

}
