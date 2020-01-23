package com.example.tkd.zvoting.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Election implements Serializable {

    @SerializedName("doctype")
    @Expose
    private String doctype;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    private final static long serialVersionUID = -7241196734610954202L;

    public boolean isFresh() {
        long startTimeSecond = Long.parseLong(startTime);
        long currenttimeSecond = System.currentTimeMillis()/1000;

        return startTime.equals("0") || currenttimeSecond<startTimeSecond;
    }

    public boolean isRunning() {
        long startTimeSecond = Long.parseLong(startTime);
        long durationSeond = Long.parseLong(duration);
        long currenttimeSecond = System.currentTimeMillis()/1000;

        return !isFresh() &&
                (currenttimeSecond>=startTimeSecond) &&
                (currenttimeSecond<=(startTimeSecond+durationSeond));
    }

    public boolean isOver() {
        return !isFresh() && !isRunning();
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("doctype", doctype).append("duration", duration).append("id", id).append("name", name).append("startTime", startTime).toString();
    }

    public long getRemainingTime() {
        long startTimeSecond = Long.parseLong(startTime);
        long durationSeond = Long.parseLong(duration);
        long currenttimeSecond = System.currentTimeMillis()/1000;

        return startTimeSecond+durationSeond-currenttimeSecond;
    }
}