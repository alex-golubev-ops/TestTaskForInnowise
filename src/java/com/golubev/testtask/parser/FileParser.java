package com.golubev.testtask.parser;

import com.golubev.testtask.builder.UserBuilder;
import com.golubev.testtask.builder.imp.UserBuilderImp;
import com.golubev.testtask.entity.Role;
import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.CanNotReadFileException;
import com.golubev.testtask.exception.CanNotWriteFileException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser implements Parser {

    private final String url;

    private final UserBuilder userBuilder = new UserBuilderImp();

    public FileParser(String url) {
        this.url = url;
    }

    @Override
    public List<User> readUsers() throws CanNotReadFileException {

        try (BufferedReader reader = new BufferedReader(new FileReader(url))) {

            List<User> users = new ArrayList<>();
            String line = null;

            while ((line = reader.readLine()) != null) {
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
            throw new CanNotWriteFileException("Can not write file",e);
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
        userBuilder.setFirstName(usersString[0]);
        userBuilder.setLastName(usersString[1]);
        userBuilder.setEmail(usersString[2]);
        userBuilder.setTelephones(parsStringToTelephones(usersString[3]));
        userBuilder.setRoles(parsStringToRoles(usersString[4]));
        return userBuilder.buildUser();
    }

    private String createStringFromUser(User user) {
        String s = user.getFirstName() + "," +
                user.getLastName() + "," +
                user.getEmail() + "," +
                parsTelephonesToString(user.getTelephones()) + "," +
                parsRolesToString(user.getRoles()) + "\n";
        return s;
    }

}
