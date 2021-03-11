package com.golubev.testtask.builder;

import com.golubev.testtask.entity.Role;
import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.valid.IncorrectEmailException;
import com.golubev.testtask.exception.valid.RoleException;
import com.golubev.testtask.exception.valid.TelephoneException;

import java.util.List;

public interface UserBuilder {

    User buildUser();


    void setFirstName(String firstName);


    void setLastName(String lastName);


    void setEmail(String email) throws IncorrectEmailException;


    void setTelephones(List<String> telephones) throws TelephoneException;

    void setRoles(List<Role> roles) throws RoleException;
}
