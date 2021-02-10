package com.golubev.testtask.builder;

import com.golubev.testtask.entity.Role;
import com.golubev.testtask.entity.User;

import java.util.List;

public interface UserBuilder {

    User buildUser();


    void setFirstName(String firstName);


    void setLastName(String lastName);


    void setEmail(String email);


    void setTelephones(List<String> telephones);

    void setRoles(List<Role> roles);
}
