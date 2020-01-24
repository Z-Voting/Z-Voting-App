package com.example.tkd.zvoting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

public class ElectionResult implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("publisherID")
    @Expose
    private String publisherID;
    @SerializedName("values")
    @Expose
    private List<Long> values = null;
    @SerializedName("electionID")
    @Expose
    private String electionID;
    @SerializedName("doctype")
    @Expose
    private String doctype;
    @SerializedName("candidates")
    @Expose
    private List<Candidate> candidates = null;
    private final static long serialVersionUID = 7616455984523407376L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ElectionResult() {
    }

    /**
     *
     * @param doctype
     * @param electionID
     * @param candidates
     * @param publisherID
     * @param values
     * @param id
     */
    public ElectionResult(String id, String publisherID, List<Long> values, String electionID, String doctype, List<Candidate> candidates) {
        super();
        this.id = id;
        this.publisherID = publisherID;
        this.values = values;
        this.electionID = electionID;
        this.doctype = doctype;
        this.candidates = candidates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public List<Long> getValues() {
        return values;
    }

    public void setValues(List<Long> values) {
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

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("publisherID", publisherID).append("values", values).append("electionID", electionID).append("doctype", doctype).append("candidates", candidates).toString();
    }

}