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
        return new User(firstName, lastName, email, telephones, roles);
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    @Override
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
