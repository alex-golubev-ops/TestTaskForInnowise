package com.golubev.testtask.application;

import com.golubev.testtask.application.state.EnterTypeUser;
import com.golubev.testtask.builder.UserBuilder;
import com.golubev.testtask.entity.Role;
import com.golubev.testtask.entity.User;
import com.golubev.testtask.exception.*;
import com.golubev.testtask.store.Store;
import com.golubev.testtask.store.UserStore;
import com.golubev.testtask.validation.UserBuilderValidator;

import java.util.*;

public class Application {
    private Scanner scanner;
    private Store storeUsers;

    public Application(Scanner scanner, String url) {

        try {
            this.scanner = scanner;
            storeUsers = new UserStore();
            storeUsers.init(url);
        } catch (CanNotReadFileException e) {
            System.out.println(e.getMessage());
        }


    }

    public void run() {
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
                case "6": {
                    exit();
                    break;
                }
                default: {
                    System.out.println("Command not recording. Please try again");
                }
            }

        } while (!"6".equals(command));

    }

    private void exit() {
        try {
            storeUsers.destroy();
        } catch (CanNotWriteFileException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.println("Delete user");
        System.out.println("Enter id user");
        String enterLine = scanner.nextLine();
        Optional<User> byId = storeUsers.findById(Long.parseLong(enterLine));
        byId.ifPresentOrElse(e -> storeUsers.delete(e),
                () -> System.out.println("User with id is not exist"));
    }

    private void updateUser() {
        System.out.println("Update user");
        String enterLine = "";
        System.out.println("Chose");
        UserBuilder userBuilder = new UserBuilderValidator();
        System.out.println("Enter id");
        enterLine = scanner.nextLine();
        User userFromStore;
        try{
            Optional<User> byId = storeUsers.findById(Long.parseLong(enterLine));
             userFromStore = byId.orElseThrow(
                    ()->{
                        System.out.println("User with id is not exist");
                        throw new RuntimeException();
                    });


        }catch (NumberFormatException e){
            System.out.println("You entered not number. Please try again");
            return;
        }
        catch (RuntimeException e){
            System.out.println("Please try again");
            return;
        }

        do {
            try {
                EnterTypeUser[] values = EnterTypeUser.values();
                for (int i = 0; i < values.length; i++) {
                    System.out.println(i + 1 + ". " + values[i]);
                }
                System.out.println(values.length + 1 + ". Exit");
                enterLine = scanner.nextLine();
                switch (enterLine) {
                    case "1": {
                        System.out.println("Enter Lastname");
                        enterLine = scanner.nextLine();
                        userBuilder.setLastName(enterLine);
                        break;
                    }
                    case "2":{
                        System.out.println("Enter Firstname");
                        enterLine = scanner.nextLine();
                        userBuilder.setFirstName(enterLine);
                        break;
                    }
                    case "3":{
                        System.out.println("Enter email:");
                        enterLine = scanner.nextLine();
                        userBuilder.setEmail(enterLine);
                        break;
                    }
                    case "4":{
                        System.out.println("Enter telephone:");
                        enterLine = scanner.nextLine();
                        List<String> newTelephones = new ArrayList<>(Arrays.asList(enterLine));
                        newTelephones.addAll(userFromStore.getTelephones());
                        userBuilder.setTelephones(newTelephones);
                        break;
                    }
                    case "5":{
                        System.out.println("Chose Role:");
                        Role[] valuesRole = Role.values();
                        for (int i = 0; i < valuesRole.length; i++) {
                            System.out.println(i + 1 + ". " + values[i]);
                        }
                        enterLine = scanner.nextLine();
                        List<Role> roles = new ArrayList<>(Arrays.asList(Role.getRole(Integer.parseInt(enterLine))));
                        roles.addAll(userFromStore.getRoles());
                        userBuilder.setRoles(roles);
                        break;
                    }
                }

                User newUser = userBuilder.buildUser();
                storeUsers.update(userFromStore,newUser);
                System.out.println("User was update");
                return;

            }catch (TelephoneException | RoleException | IncorrectEmailException e){
                System.out.println(e.getMessage()+"\nPlease try again");
                return;
            }

        } while (true);

    }

    private void findUserById() {
        System.out.println("Find user by id");
        String enterLine = "";

        do {

            try {

                System.out.println("Enter id:");
                enterLine = scanner.nextLine();

                Optional<User> byId = storeUsers.findById(Long.parseLong(enterLine));

                byId.ifPresentOrElse(System.out::println,
                        () -> System.out.println("User with id is not exist"));
                return;

            } catch (NumberFormatException e) {

                System.out.println("You entered not number. Please try again");

            }

        } while (true);

    }

    private void createUser() {
        EnterTypeUser typeEnter = EnterTypeUser.FIRSTNAME;
        UserBuilder userBuilder = new UserBuilderValidator();
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
                storeUsers.save(newUser);
                System.out.println("User was created");
                return;

            } catch (IncorrectEmailException | RoleException | TelephoneException e) {
                System.out.println(e.getMessage() + "\nPlease try again");
            } catch (NumberFormatException e) {
                System.out.println("You entered not number. Please try again");
            }
        } while (true);

    }

    private void findAll() {
        System.out.println("Find all user ");
        List<User> all = storeUsers.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }


}
