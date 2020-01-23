package com.example.tkd.zvoting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Vote implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("voterID")
    @Expose
    private String voterID;
    @SerializedName("values")
    @Expose
    private String values;
    @SerializedName("electionID")
    @Expose
    private String electionID;
    @SerializedName("doctype")
    @Expose
    private String doctype;
    private final static long serialVersionUID = 3009877941632861163L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Vote() {
    }

    /**
     *
     * @param doctype
     * @param electionID
     * @param values
     * @param voterID
     * @param id
     */
    public Vote(String id, String voterID, String values, String electionID, String doctype) {
        super();
        this.id = id;
        this.voterID = voterID;
        this.values = values;
        this.electionID = electionID;
        this.doctype = doctype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoterID() {
        return voterID;
    }

    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("voterID", voterID).append("values", values).append("electionID", electionID).append("doctype", doctype).toString();
    }

}