package com.example.tkd.zvoting.rest;

import com.example.tkd.zvoting.model.Voter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class LoginResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Voter data;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -2554450009593906821L;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginResponse() {
    }

    /**
     *
     * @param data
     * @param message
     * @param status
     */
    public LoginResponse(String status, Voter data, String message) {
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

    public Voter getData() {
        return data;
    }

    public void setData(Voter data) {
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
