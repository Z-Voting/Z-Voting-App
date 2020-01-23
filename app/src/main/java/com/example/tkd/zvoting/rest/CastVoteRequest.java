package com.example.tkd.zvoting.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class CastVoteRequest implements Serializable
{

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("electionID")
    @Expose
    private String electionID;
    @SerializedName("voteContent")
    @Expose
    private String voteContent;
    private final static long serialVersionUID = 5653570959699010389L;

    /**
     * No args constructor for use in serialization
     *
     */
    public CastVoteRequest() {
    }

    /**
     *
     * @param electionID
     * @param voteContent
     * @param email
     */
    public CastVoteRequest(String email, String electionID, String voteContent) {
        super();
        this.email = email;
        this.electionID = electionID;
        this.voteContent = voteContent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getElectionID() {
        return electionID;
    }

    public void setElectionID(String electionID) {
        this.electionID = electionID;
    }

    public String getVoteContent() {
        return voteContent;
    }

    public void setVoteContent(String voteContent) {
        this.voteContent = voteContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("email", email).append("electionID", electionID).append("voteContent", voteContent).toString();
    }

}
