package com.golubev.testtask;

import com.golubev.testtask.application.Application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Application application = new Application(scanner,"src/resourses/users.txt");
        application.run();
    }
}
