package com.golubev.testtask.factory;

import com.golubev.testtask.dao.UserListDao;
import com.golubev.testtask.dao.impl.UserListDaoImpl;

public class DaoFactory {
    private static DaoFactory INSTANCE;

    private final UserListDao listDao;

    private DaoFactory(){
        listDao = new UserListDaoImpl();
    }

    public static DaoFactory getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DaoFactory();
        }
        return INSTANCE;
    }

    public UserListDao getDao(){
        return listDao;
    }
}
