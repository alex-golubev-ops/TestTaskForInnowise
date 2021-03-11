package com.golubev.testtask.validation.api;

import com.golubev.testtask.entity.Role;
import com.golubev.testtask.exception.valid.RoleException;
import com.golubev.testtask.validation.model.UserValidator;
import java.util.List;
import java.util.stream.Collectors;

public class UserValidatorImpl implements UserValidator {

    @Override
    public void check(List<Role> roles) throws RoleException {

        if (roles.contains(Role.SUPER_ADMIN) && roles.size() > 1) {
            throw new RoleException("SUPER_ADMIN should not have more roles");
        }

        Role[] values = Role.values();

        for (Role value : values) {
            List<Role> collectOfRole = roles
                    .stream()
                    .filter(e -> e.equals(value))
                    .collect(Collectors.toList());
            if (collectOfRole.size() > 1) {
                throw new RoleException("Roles have a duplicate level");
            }
        }
    }
}
