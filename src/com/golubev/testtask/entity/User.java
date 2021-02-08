package com.golubev.testtask.entity;

import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private List<String> telephony;
    private List<Role> roles;

    public User(String firstName, String lastName, String email, List<String> telephony, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephony = telephony;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getTelephony() {
        return telephony;
    }

    public void setTelephony(List<String> telephony) {
        this.telephony = telephony;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
