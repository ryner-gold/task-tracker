package com.tasktracker.model.response;

import com.tasktracker.model.TaskList;

import java.util.Optional;

public abstract class TaskResponse {
    protected final TaskList taskList;
    protected final String statusCode;
    protected final String message;

    protected TaskResponse (TaskList taskList, String statusCode, String message) {
        this.taskList = taskList;
        this.statusCode = statusCode;
        this.message = message;
    }

    public Optional<TaskList> getTaskList () {
        return Optional.ofNullable (taskList);
    }

    public String getStatusCode () {
        return statusCode;
    }

    public String getMessage () {
        return message;
    }
}