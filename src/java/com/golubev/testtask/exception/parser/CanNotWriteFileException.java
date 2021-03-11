package com.golubev.testtask.exception.parser;

public class CanNotWriteFileException extends Exception {
    public CanNotWriteFileException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
