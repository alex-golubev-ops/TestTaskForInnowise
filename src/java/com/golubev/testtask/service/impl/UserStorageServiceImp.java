package com.golubev.testtask.service.impl;

import com.golubev.testtask.dao.UserListDao;
import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.dao.DaoException;
import com.golubev.testtask.exception.entity.UserAlredyExistException;
import com.golubev.testtask.exception.entity.UserNotExistException;
import com.golubev.testtask.exception.valid.UserStorageServiceException;
import com.golubev.testtask.factory.DaoFactory;
import com.golubev.testtask.service.UserStorageService;
import com.golubev.testtask.validation.UserBuilderValidator;
import java.util.List;
import java.util.Optional;

public class UserStorageServiceImp implements UserStorageService {

    private final UserBuilderValidator userBuilderValidator = new UserBuilderValidator();
    private final DaoFactory factory = DaoFactory.getInstance();
    private final UserListDao listDao = factory.getDao();

    @Override
    public void add(User user) throws UserStorageServiceException {
        try {
            List<User> users = listDao.findAll();
            if (users.contains(user)) {
                throw new UserAlredyExistException("User with id  " + user.getId() + " already exist");
            }
            listDao.add(user);
        } catch (UserAlredyExistException | DaoException e) {
            throw new UserStorageServiceException(e.getMessage());
        }

    }

    @Override
    public void remove(User user) throws UserStorageServiceException {
        try {
            listDao.remove(user);
        } catch (DaoException e) {
            throw new UserStorageServiceException(e.getMessage());
        }

    }

    @Override
    public void update(User oldUser, User newUser) throws UserStorageServiceException {

        try {
            List<User> users = listDao.findAll();

            if (!users.contains(oldUser)) {
                throw new UserNotExistException("User with id " + oldUser.getId() + " is not exist");
            }

            if (newUser.getFirstName() != null) {
                oldUser.setFirstName(newUser.getFirstName());
            }

            if (newUser.getLastName() != null) {
                oldUser.setLastName(newUser.getLastName());
            }

            if (newUser.getEmail() != null) {
                oldUser.setEmail(newUser.getEmail());
            }

            if (newUser.getTelephones() != null) {
                oldUser.setTelephones(newUser.getTelephones());
            }

            if (newUser.getRoles() != null) {
                oldUser.setRoles(newUser.getRoles());
            }
            listDao.update(oldUser);

        } catch (DaoException | UserNotExistException e) {
            throw new UserStorageServiceException(e.getMessage());
        }
    }

    @Override
    public User findById(long id) throws UserStorageServiceException {
        try {
            Optional<User> user = listDao.findById(id);
            return user.orElseThrow(() -> new UserNotExistException("User with id " + id + " not exist "));
        } catch (DaoException | UserNotExistException e) {
            throw new UserStorageServiceException(e.getMessage());
        }
    }


    @Override
    public List<User> findAll() throws UserStorageServiceException {
        try {
            List<User> users = listDao.findAll();
            return users;
        } catch (DaoException e) {
            throw new UserStorageServiceException(e.getMessage());
        }
    }
}
