package com.golubev.testtask.application;

import com.golubev.testtask.application.state.EnterTypeUser;
import com.golubev.testtask.builder.UserBuilder;
import com.golubev.testtask.entity.Role;
import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.valid.*;
import com.golubev.testtask.service.UserStorageService;
import com.golubev.testtask.service.impl.UserStorageServiceImp;
import com.golubev.testtask.validation.UserBuilderValidator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {
    private final InputStream inputStream;
    private final UserStorageService service;
    private final UserBuilder userBuilder;

    public Application(InputStream inputStream) {
        service = new UserStorageServiceImp();
        this.inputStream = inputStream;
        userBuilder = new UserBuilderValidator();

    }

    public void run() {
        Scanner scanner = new Scanner(inputStream);
        String command = "";

        do {
            System.out.println("Enter number from 1 to 6");
            System.out.println("->1. Create user");
            System.out.println("->2. Enter user by id");
            System.out.println("->3. Enter all users");
            System.out.println("->4. Update user");
            System.out.println("->5. Delete user");
            System.out.println("->6. Exist");

            command = scanner.nextLine();

            switch (command) {
                case "1": {
                    createUser();
                    break;
                }
                case "2": {
                    findUserById();
                    break;
                }
                case "3": {
                    findAll();
                    break;
                }
                case "4": {
                    updateUser();
                    break;
                }
                case "5": {
                    deleteUser();
                    break;
                }
                default: {
                    System.out.println("Command not recording. Please try again");
                }
            }

        } while (!"6".equals(command));

    }


    private void deleteUser() {
        Scanner scanner = new Scanner(inputStream);
        System.out.println("Delete user");
        System.out.println("Enter id user");
        if(!scanner.hasNextLong()){
            System.out.println("You entered not long number");
            deleteUser();
            return;
        }
        User user = new User();
        user.setId(scanner.nextLong());
        try {
            service.remove(user);
            System.out.println("User was remove");
        } catch (UserStorageServiceException e) {
            System.out.println(e.getMessage());
            deleteUser();
        }
    }

    private void updateUser() {
        Scanner scanner = new Scanner(inputStream);
        System.out.println("Update user");
        String enterLine = "";
        System.out.println("Chose(enter id)");
        try {
            List<User> all = service.findAll();
            all.forEach(System.out::println);
            if(!scanner.hasNextLong()){
                System.out.println("You entered is not long number");
                updateUser();
                return;
            }

            User userFromDao = service.findById(scanner.nextLong());
            do {
            try {
                EnterTypeUser[] values = EnterTypeUser.values();
                for (int i = 0; i < values.length; i++) {
                    System.out.println(i + 1 + ". " + values[i]);
                }
                System.out.println(values.length + 1 + ". Exit");
                scanner.nextLine();
                enterLine = scanner.nextLine();
                switch (enterLine) {
                    case "1": {
                        System.out.println("Enter Lastname");
                        enterLine = scanner.nextLine();
                        userBuilder.setLastName(enterLine);
                        break;
                    }
                    case "2": {
                        System.out.println("Enter Firstname");
                        enterLine = scanner.nextLine();
                        userBuilder.setFirstName(enterLine);
                        break;
                    }
                    case "3": {
                        System.out.println("Enter email:");
                        enterLine = scanner.nextLine();
                        userBuilder.setEmail(enterLine);
                        break;
                    }
                    case "4": {
                        System.out.println("Enter telephone:");
                        enterLine = scanner.nextLine();
                        List<String> newTelephones = new ArrayList<>(Arrays.asList(enterLine));
                        newTelephones.addAll(userFromDao.getTelephones());
                        userBuilder.setTelephones(newTelephones);
                        break;
                    }
                    case "5": {
                        System.out.println("Chose Role:");
                        Role[] valuesRole = Role.values();
                        for (int i = 0; i < valuesRole.length; i++) {
                            System.out.println(i + 1 + ". " + values[i]);
                        }
                        enterLine = scanner.nextLine();
                        List<Role> roles = new ArrayList<>(Arrays.asList(Role.getRole(Integer.parseInt(enterLine))));
                        roles.addAll(userFromDao.getRoles());
                        userBuilder.setRoles(roles);
                        break;
                    }
                }

                User newUser = userBuilder.buildUser();
                service.update(userFromDao, newUser);
                System.out.println("User was update");
                return;

            } catch (UserValidException e) {
                System.out.println(e.getMessage() + "\nPlease try again");
            }

            } while (true);
        } catch (UserStorageServiceException e) {
            System.out.println(e.getMessage());
            updateUser();
        }

    }

    private void findUserById() {
        Scanner scanner = new Scanner(inputStream);
        System.out.println("Find user by id");

        String enterLine = "";

        do {

            try {
                System.out.println("Enter id: ");
                if(!scanner.hasNextLong()){
                    System.out.println("You entered not long number");
                    continue;
                }
                User user = service.findById(scanner.nextLong());
                System.out.println(user);
                return;

            } catch (UserStorageServiceException e) {
                System.out.println(e.getMessage());
            }

        } while (true);

    }

    private void createUser() {
        Scanner scanner = new Scanner(inputStream);
        EnterTypeUser typeEnter = EnterTypeUser.FIRSTNAME;
        System.out.println("Creating user");
        String enterLine = "";
        do {
            try {
                switch (typeEnter) {
                    case FIRSTNAME: {
                        System.out.println("Enter firstname:");
                        enterLine = scanner.nextLine();
                        userBuilder.setFirstName(enterLine);
                        typeEnter = EnterTypeUser.LASTNAME;
                    }
                    case LASTNAME: {
                        System.out.println("Enter lastname:");
                        enterLine = scanner.nextLine();
                        userBuilder.setLastName(enterLine);
                        typeEnter = EnterTypeUser.EMAIL;
                    }
                    case EMAIL: {
                        System.out.println("Enter email:");
                        enterLine = scanner.nextLine();
                        userBuilder.setEmail(enterLine);
                        typeEnter = EnterTypeUser.TELEPHONES;
                    }
                    case TELEPHONES: {
                        System.out.println("Enter telephone:");
                        enterLine = scanner.nextLine();
                        userBuilder.setTelephones(Arrays.asList(enterLine));
                        typeEnter = EnterTypeUser.ROLES;
                    }
                    case ROLES: {
                        System.out.println("Chose Role:");
                        Role[] values = Role.values();
                        for (int i = 0; i < values.length; i++) {
                            System.out.println(i + 1 + ". " + values[i]);
                        }
                        enterLine = scanner.nextLine();
                        userBuilder.setRoles(Arrays.asList(Role.getRole(Integer.parseInt(enterLine))));
                    }
                }

                User newUser = userBuilder.buildUser();
                service.add(newUser);
                System.out.println("User was created");
                return;

            } catch (UserValidException e) {
                System.out.println(e.getMessage() + "\nPlease try again");
            }  catch (UserStorageServiceException e) {
                System.out.println(e.getMessage());
                return;
            }
        } while (true);

    }

    private void findAll() {
        System.out.println("Find all user ");
        try {

            List<User> all = service.findAll();
            for (User user : all) {
                System.out.println(user);
            }
        } catch (UserStorageServiceException e) {
            System.out.println(e.getMessage());
        }
    }


}
