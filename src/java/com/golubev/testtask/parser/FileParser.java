package com.golubev.testtask.parser;

import com.golubev.testtask.entity.Role;
import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.parser.CanNotReadFileException;
import com.golubev.testtask.exception.parser.CanNotWriteFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser implements Parser {

    private final String url;

    public FileParser(String url) {
        this.url = url;
    }

    @Override
    public List<User> readUsers() throws CanNotReadFileException {

        try (BufferedReader reader = new BufferedReader(new FileReader(url))) {

            List<User> users = new ArrayList<>();
            String line = null;

            while ((line = reader.readLine()) != null&& !line.isEmpty()) {
                User user = createUserFromString(line);
                users.add(user);
            }

            return users;
        } catch (IOException e) {
            throw new CanNotReadFileException("Can not read file", e);
        }

    }

    @Override
    public void writeUsers(List<User> users) throws CanNotWriteFileException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(url))) {

            for (User user : users) {
                writer.write(createStringFromUser(user));
            }

        } catch (IOException e) {
            throw new CanNotWriteFileException("Can not write file", e);
        }
    }

    private List<String> parsStringToTelephones(String telephones) {
        String[] split = telephones.split("\\|");
        return Arrays.asList(split);
    }

    private String parsTelephonesToString(List<String> telephones) {
        StringBuilder result = new StringBuilder();

        for (String telephone : telephones) {
            result.append(telephone).append("|");
        }

        return result.toString();
    }

    private List<Role> parsStringToRoles(String roles) {
        String[] split = roles.split("\\|");
        return Arrays
                .stream(split)
                .map(Role::valueOf)
                .collect(Collectors.toList());
    }

    private String parsRolesToString(List<Role> roles) {
        StringBuilder result = new StringBuilder();

        for (Role role : roles) {
            result.append(role).append("|");
        }

        return result.toString();
    }

    private User createUserFromString(String line) {
        String[] usersString = line.split(",");
        User user = new User();
        user.setId(Long.parseLong(usersString[0]));
        user.setLastName(usersString[1]);
        user.setFirstName(usersString[2]);
        user.setEmail(usersString[3]);
        user.setTelephones(parsStringToTelephones(usersString[4]));
        user.setRoles(parsStringToRoles(usersString[5]));
        return user;
    }

    private String createStringFromUser(User user) {
        String s = user.getId() + "," +
                user.getFirstName() + "," +
                user.getLastName() + "," +
                user.getEmail() + "," +
                parsTelephonesToString(user.getTelephones()) + "," +
                parsRolesToString(user.getRoles()) + "\n";
        return s;
    }

}
