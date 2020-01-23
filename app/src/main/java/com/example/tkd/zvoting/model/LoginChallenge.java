package com.example.tkd.zvoting.model;

public class LoginChallenge {
    long a1;
    long a2;
    long a3;
    long n;

    public LoginChallenge() {
    }

    public LoginChallenge(long a1, long a2, long a3, long n) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.n = n;
    }

    public long getA1() {
        return a1;
    }

    public void setA1(long a1) {
        this.a1 = a1;
    }

    public long getA2() {
        return a2;
    }

    public void setA2(long a2) {
        this.a2 = a2;
    }

    public long getA3() {
        return a3;
    }

    public void setA3(long a3) {
        this.a3 = a3;
    }

    public long getN() {
        return n;
    }

    public void setN(long n) {
        this.n = n;
    }
}
