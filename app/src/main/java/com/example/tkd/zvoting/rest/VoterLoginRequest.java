package com.example.tkd.zvoting.rest;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class VoterLoginRequest implements Serializable
{

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("a1")
    @Expose
    private String a1;
    @SerializedName("a2")
    @Expose
    private String a2;
    @SerializedName("a3")
    @Expose
    private String a3;
    @SerializedName("v1")
    @Expose
    private String v1;
    @SerializedName("v2")
    @Expose
    private String v2;
    @SerializedName("v3")
    @Expose
    private String v3;
    @SerializedName("y1")
    @Expose
    private String y1;
    private final static long serialVersionUID = -4618438202281495886L;

    /**
     * No args constructor for use in serialization
     *
     */
    public VoterLoginRequest() {
    }

    /**
     *
     * @param a1
     * @param a2
     * @param a3
     * @param x
     * @param y1
     * @param v1
     * @param v2
     * @param v3
     * @param email
     */
    public VoterLoginRequest(String email, String x, String a1, String a2, String a3, String v1, String v2, String v3, String y1) {
        super();
        this.email = email;
        this.x = x;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.y1 = y1;
    }

    String str(long val) {
        return String.valueOf(val);
    }

    public VoterLoginRequest(String email, long x, long a1, long a2, long a3, long v1, long v2, long v3, long y1) {
        this.email = email;
        this.x = str(x);
        this.a1 = str(a1);
        this.a2 = str(a2);
        this.a3 = str(a3);
        this.v1 = str(v1);
        this.v2 = str(v2);
        this.v3 = str(v3);
        this.y1 = str(y1);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
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

    public String getY1() {
        return y1;
    }

    public void setY1(String y1) {
        this.y1 = y1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("email", email).append("x", x).append("a1", a1).append("a2", a2).append("a3", a3).append("v1", v1).append("v2", v2).append("v3", v3).append("y1", y1).toString();
    }

}
