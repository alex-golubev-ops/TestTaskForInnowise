package com.golubev.testtask.store;

import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.CanNotReadFileException;
import com.golubev.testtask.exception.CanNotWriteFileException;
import com.golubev.testtask.parser.Parser;

import java.util.List;
import java.util.Optional;

public interface Store {

    void init(String url) throws CanNotReadFileException;

    void destroy() throws CanNotWriteFileException;

    List<User> findAll();

    Optional<User> findById(Long id);

    void save(User user);

    void update(User user);

    void delete(User user);

}
