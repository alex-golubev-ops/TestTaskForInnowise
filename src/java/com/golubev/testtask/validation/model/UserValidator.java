package com.golubev.testtask.validation.model;

import com.golubev.testtask.entity.Role;
import java.util.List;

public interface UserValidator {

    void check(List<Role> roles);

}
