package com.example.tkd.zvoting.model;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String email;
    public String s1, s2, s3;

    public User() {

    }


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
