package com.tasktracker.error;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException (String message) {
        super (message);
    }
}
