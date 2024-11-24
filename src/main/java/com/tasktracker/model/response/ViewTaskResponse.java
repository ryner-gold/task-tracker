package com.tasktracker.model.response;

import com.tasktracker.model.Task;

import java.util.Optional;

public final class ViewTaskResponse {
    private final Task task;
    private final String statusCode;
    private final String message;

    private ViewTaskResponse (ViewTaskResponse.Builder builder) {
        this.task = builder.task;
        this.statusCode = builder.statusCode;
        this.message = builder.message;
    }

    public Optional<Task> getTask () {
        return Optional.ofNullable (task);
    }

    public String getStatusCode () {
        return statusCode;
    }

    public String getMessage () {
        return message;
    }

    public final static class Builder {
        private Task task;
        private String statusCode;
        private String message;

        public ViewTaskResponse.Builder task (Task task) {
            this.task = task;
            return this;
        }

        public ViewTaskResponse.Builder statusCode (String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ViewTaskResponse.Builder message (String message) {
            this.message = message;
            return this;
        }

        public ViewTaskResponse build () {
            return new ViewTaskResponse (this);
        }
    }

}
