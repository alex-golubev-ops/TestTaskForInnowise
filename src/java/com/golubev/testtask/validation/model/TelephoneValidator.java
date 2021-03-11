package com.golubev.testtask.validation.model;

import com.golubev.testtask.exception.valid.TelephoneException;

import java.util.List;

public interface TelephoneValidator {

    void check(List<String> telephones) throws TelephoneException;
}
