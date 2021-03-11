package com.golubev.testtask;

import com.golubev.testtask.application.Application;

public class Main {
    public static void main(String[] args) {
        Application application = new Application(System.in);
        application.run();
    }
}
