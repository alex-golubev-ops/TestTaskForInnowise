package com.golubev.testtask.validation;

import com.golubev.testtask.builder.UserBuilder;
import com.golubev.testtask.builder.imp.UserBuilderImp;
import com.golubev.testtask.entity.Role;
import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.valid.IncorrectEmailException;
import com.golubev.testtask.exception.valid.RoleException;
import com.golubev.testtask.exception.valid.TelephoneException;
import com.golubev.testtask.validation.api.EmailValidatorImpl;
import com.golubev.testtask.validation.api.TelephoneValidatorImpl;
import com.golubev.testtask.validation.api.UserValidatorImpl;
import com.golubev.testtask.validation.model.EmailValidator;
import com.golubev.testtask.validation.model.TelephoneValidator;
import com.golubev.testtask.validation.model.UserValidator;
import java.util.List;

public class UserBuilderValidator implements UserBuilder {

    private final UserBuilderImp userBuilder = new UserBuilderImp();

    private final EmailValidator emailValidator = new EmailValidatorImpl();

    private final TelephoneValidator telephoneValidator = new TelephoneValidatorImpl();

    private final UserValidator userValidator = new UserValidatorImpl();


    @Override
    public User buildUser() {
        return userBuilder.buildUser();
    }


    @Override
    public void setFirstName(String firstName) {
        userBuilder.setFirstName(firstName);
    }


    @Override
    public void setLastName(String lastName) {
        userBuilder.setLastName(lastName);
    }

    @Override
    public void setEmail(String email) throws IncorrectEmailException {
        emailValidator.check(email);
        userBuilder.setEmail(email);
    }

    @Override
    public void setTelephones(List<String> telephones) throws TelephoneException {
        telephoneValidator.check(telephones);
        userBuilder.setTelephones(telephones);
    }


    @Override
    public void setRoles(List<Role> roles) throws RoleException {
        userValidator.check(roles);
        userBuilder.setRoles(roles);
    }

}
