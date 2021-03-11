package com.golubev.testtask.entity;

import com.golubev.testtask.exception.valid.RoleException;

public enum Role {
    USER(1),
    CUSTOMER(1),
    ADMIN(2),
    PROVIDER(2),
    SUPER_ADMIN(3);

    private final int level;

    Role(int level){
        this.level=level;
    }

    public static Role getRole(int number) throws RoleException {
        switch (number){
            case 1:{

                return Role.USER;

            }
            case 2:{

                return Role.CUSTOMER;

            }
            case 3:{

                return Role.ADMIN;

            }
            case 4:{

                return Role.PROVIDER;

            }
            case 5:{

                return Role.SUPER_ADMIN;


            }
            default: throw new RoleException("Not found role type with number " + number);
        }
    }
}
