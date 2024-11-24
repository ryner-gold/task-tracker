package com.tasktracker.error;

public class TaskServiceException extends RuntimeException {
    public TaskServiceException(String message) {
        super(message);
    }
}
