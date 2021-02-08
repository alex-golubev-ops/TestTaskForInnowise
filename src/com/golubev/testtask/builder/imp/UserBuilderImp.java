package com.golubev.testtask.builder.imp;

import com.golubev.testtask.builder.UserBuilder;
import com.golubev.testtask.entity.Role;
import com.golubev.testtask.entity.User;

import java.util.List;

public class UserBuilderImp implements UserBuilder {

    private String firstName;
    private String lastName;
    private String email;
    private List<String> telephones;
    private List<Role> roles;

    @Override
    public User buildUser() {
        return new User(firstName,lastName,email,telephones,roles);
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

    public List<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
