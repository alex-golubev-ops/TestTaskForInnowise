package com.golubev.testtask.validation.api;

import com.golubev.testtask.exception.IncorrectEmailException;
import com.golubev.testtask.validation.model.EmailValidator;

public class EmailValidatorImpl implements EmailValidator {
    private final String pattern = "[\\w.-]*@[\\w]*\\.[\\w]*";

    @Override
    public void check(String email) {

        if (!email.matches(pattern)) {
            throw new IncorrectEmailException("Email is not correct! Enter email for pattern *****@***.*");
        }
    }
}
