package com.example.tkd.zvoting.model;


import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class ResponseMessage implements Serializable
{
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -8120151689709330544L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseMessage() {
    }

    /**
     *
     * @param data
     * @param message
     * @param status
     */
    public ResponseMessage(String status, String data, String message) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
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