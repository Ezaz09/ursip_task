package com.testtask.ursip.exception;

public class TaskException extends RuntimeException {

    public TaskException(String message, Object... params) {
        super(message);
    }
}
