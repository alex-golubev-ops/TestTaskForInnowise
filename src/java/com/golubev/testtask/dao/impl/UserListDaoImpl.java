package com.golubev.testtask.dao.impl;

import com.golubev.testtask.dao.UserListDao;
import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.parser.CanNotReadFileException;
import com.golubev.testtask.exception.parser.CanNotWriteFileException;
import com.golubev.testtask.exception.dao.DaoException;
import com.golubev.testtask.parser.FileParser;
import com.golubev.testtask.parser.Parser;
import java.util.List;
import java.util.Optional;

public class UserListDaoImpl implements UserListDao {
    private final String url = "./src/resourses/users.txt";

    @Override
    public void add(User user) throws DaoException {

        Parser parser = new FileParser(url);

        try {

            List<User> users = parser.readUsers();
            users.add(user);
            parser.writeUsers(users);

        } catch (CanNotReadFileException | CanNotWriteFileException e) {
            throw new DaoException("Can not add user to file " + e.getMessage());
        }

    }

    @Override
    public void remove(User user) throws DaoException {
        Parser parser = new FileParser(url);

        try {

            List<User> users = parser.readUsers();
            users.remove(user);
            parser.writeUsers(users);

        } catch (CanNotReadFileException | CanNotWriteFileException e) {
            throw new DaoException("Can not remove user from file " + e.getMessage());
        }
    }

    @Override
    public void update(User user) throws DaoException {
        Parser parser = new FileParser(url);

        try {

            List<User> users = parser.readUsers();

            for (User findUser : users) {
                if (findUser.getId() == user.getId()) {
                    findUser.setFirstName(user.getFirstName());
                    findUser.setLastName(user.getLastName());
                    findUser.setEmail(user.getEmail());
                    findUser.setRoles(user.getRoles());
                    findUser.setTelephones(user.getTelephones());

                }
            }
            parser.writeUsers(users);

        } catch (CanNotReadFileException | CanNotWriteFileException e) {
            throw new DaoException("Can not update user to file " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        Parser parser = new FileParser(url);

        try {

            List<User> users = parser.readUsers();
            Optional<User> any = users
                    .stream()
                    .filter(e -> e.getId() == id)
                    .findAny();
            return any;

        } catch (CanNotReadFileException e) {
            throw new DaoException("Can not find by id from file " + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        Parser parser = new FileParser(url);

        try {

            List<User> users = parser.readUsers();
            return users;

        } catch (CanNotReadFileException e) {
            throw new DaoException("Can not find all " + e.getMessage());
        }
    }
}
