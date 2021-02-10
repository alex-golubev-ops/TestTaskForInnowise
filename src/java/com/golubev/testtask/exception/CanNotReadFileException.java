package com.golubev.testtask.exception;

public class CanNotReadFileException extends Exception {

    public CanNotReadFileException(String message,Throwable throwable) {
        super(message,throwable);
    }
}
