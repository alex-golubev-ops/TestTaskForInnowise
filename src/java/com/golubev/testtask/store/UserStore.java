package com.golubev.testtask.store;

import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.CanNotReadFileException;
import com.golubev.testtask.exception.CanNotWriteFileException;
import com.golubev.testtask.parser.FileParser;
import com.golubev.testtask.parser.Parser;

import java.util.List;
import java.util.Optional;

public class UserStore implements Store {

    private List<User> users;

    private Parser parser;

    @Override
    public void init(String url) throws CanNotReadFileException {
        parser = new FileParser(url);
        users = parser.readUsers();
    }

    @Override
    public void destroy() throws CanNotWriteFileException {
       parser.writeUsers(users);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users
                .stream()
                .filter(e -> e.getId().equals(id))
                .findAny();
    }

    @Override
    public void save(User user) {
        users.add(user);
    }


    @Override
    public void update(User oldUser, User newUser) {
        if(newUser.getFirstName()!=null){
            oldUser.setFirstName(newUser.getFirstName());
        }
        if(newUser.getLastName()!=null){
            oldUser.setLastName(newUser.getLastName());
        }
        if(newUser.getEmail()!=null){
            oldUser.setEmail(newUser.getEmail());
        }
        if(newUser.getTelephones()!=null){
            oldUser.setTelephones(newUser.getTelephones());
        }
        if(newUser.getRoles()!=null){
            oldUser.setRoles(newUser.getRoles());
        }
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
