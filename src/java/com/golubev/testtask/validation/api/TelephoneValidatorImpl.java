package com.golubev.testtask.validation.api;

import com.golubev.testtask.exception.valid.TelephoneException;
import com.golubev.testtask.validation.model.TelephoneValidator;
import java.util.List;

public class TelephoneValidatorImpl implements TelephoneValidator {
    private final String pattern="^(375[0-9]{9})$";
    @Override
    public void check(List<String> telephones) throws TelephoneException {

        if (telephones.size() > 3) {
            throw new TelephoneException("You can have no more than 3 telephones");
        }

        for (String telephone : telephones) {
            if (!telephone.matches(pattern)) {
                throw new TelephoneException("Telephones are not correct!" +
                        " Check your telephones for pattern 375*********");
            }
        }

    }
}
