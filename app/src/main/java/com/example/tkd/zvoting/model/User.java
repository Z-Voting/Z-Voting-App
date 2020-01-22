package com.example.tkd.zvoting.model;

import java.io.Serializable;

public class User implements Serializable {
    public String Name;
    public String Email;
    public String S1, S2, S3;

    public User() {

    }


    public User(String name, String email) {
        this.Name = name;
        this.Email = email;
    }
}
