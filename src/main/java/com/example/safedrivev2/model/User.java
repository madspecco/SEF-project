package com.example.safedrivev2.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

import java.util.ArrayList;

public class User {

    private int acountId;
    private String email;
    private String username;
    private String firstname;
    private String lastname;


    public User() {
    }


    public int getAcountId() {
        return acountId;
    }

    public void setAcountId(int acountId) {
        this.acountId = acountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String toString() {
        return "User{" +
                "acountId=" + acountId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}