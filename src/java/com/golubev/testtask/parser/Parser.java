package com.golubev.testtask.parser;

import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.CanNotReadFileException;
import com.golubev.testtask.exception.CanNotWriteFileException;

import java.util.List;

public interface Parser {

    List<User> readUsers() throws CanNotReadFileException;

    void writeUsers(List<User> users) throws CanNotWriteFileException;
}
