package com.golubev.testtask.service;

import com.golubev.testtask.entity.Role;
import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.valid.UserStorageServiceException;

import java.util.List;

public interface UserStorageService {

    void add(User user) throws UserStorageServiceException;

    void remove(User user) throws UserStorageServiceException;

    void update(User oldUser, User newUser) throws UserStorageServiceException;

    User findById(long id) throws UserStorageServiceException;

    List<User> findAll() throws UserStorageServiceException;
}
