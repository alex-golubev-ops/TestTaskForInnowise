package com.golubev.testtask.validation.model;

import com.golubev.testtask.exception.valid.IncorrectEmailException;

public interface EmailValidator {

    void check(String email) throws IncorrectEmailException;

}
