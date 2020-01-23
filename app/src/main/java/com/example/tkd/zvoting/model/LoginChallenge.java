package com.example.tkd.zvoting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;

public class LoginChallenge implements Serializable
{

    @SerializedName("a1")
    @Expose
    private Long a1;
    @SerializedName("a2")
    @Expose
    private Long a2;
    @SerializedName("a3")
    @Expose
    private Long a3;
    @SerializedName("n")
    @Expose
    private Long n;
    private final static long serialVersionUID = -1650088854618052301L;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginChallenge() {
    }

    /**
     *
     * @param a1
     * @param a2
     * @param a3
     * @param n
     */
    public LoginChallenge(Long a1, Long a2, Long a3, Long n) {
        super();
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.n = n;
    }

    public Long getA1() {
        return a1;
    }

    public void setA1(Long a1) {
        this.a1 = a1;
    }

    public Long getA2() {
        return a2;
    }

    public void setA2(Long a2) {
        this.a2 = a2;
    }

    public Long getA3() {
        return a3;
    }

    public void setA3(Long a3) {
        this.a3 = a3;
    }

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("a1", a1).append("a2", a2).append("a3", a3).append("n", n).toString();
    }

}