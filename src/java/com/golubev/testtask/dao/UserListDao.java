package com.golubev.testtask.dao;

import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserListDao {

    void add(User user) throws DaoException;

    void remove(User user) throws DaoException;

    void update(User user) throws DaoException;

    Optional<User> findById(long id) throws DaoException;

    List<User> findAll() throws DaoException;

}
