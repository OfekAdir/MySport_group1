package com.example.firebase_example;

import java.util.ArrayList;

public class User {
    private String email;
    private String pass;
    private int age;
    private String address;
    private String sports;


    public User() {

    }

    public User(String email, String pass, int age , String address , String sp) {
        this.email = email;
        this.pass = pass;
        this.age = age;
        this.address = address;
        this.sports = sp;
    }



    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getSports() {
        return sports;
    }
}
