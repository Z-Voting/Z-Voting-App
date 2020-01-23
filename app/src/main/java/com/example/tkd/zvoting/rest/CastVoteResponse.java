package com.example.tkd.zvoting.rest;

import com.example.tkd.zvoting.model.Vote;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class CastVoteResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Vote data;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 9216839916904348263L;

    /**
     * No args constructor for use in serialization
     *
     */
    public CastVoteResponse() {
    }

    /**
     *
     * @param data
     * @param message
     * @param status
     */
    public CastVoteResponse(String status, Vote data, String message) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vote getData() {
        return data;
    }

    public void setData(Vote data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("data", data).append("message", message).toString();
    }

}