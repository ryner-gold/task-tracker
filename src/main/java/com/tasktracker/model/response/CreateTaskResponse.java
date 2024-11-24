package com.tasktracker.model.response;

import com.tasktracker.model.TaskList;

public class CreateTaskResponse extends TaskResponse {

    private CreateTaskResponse (Builder builder) {
        super (builder.task,
               builder.statusCode,
               builder.message);
    }

    public static class Builder {
        private TaskList task;
        private String statusCode;
        private String message;

        public Builder taskList (TaskList taskList) {
            this.task = taskList;
            return this;
        }

        public Builder statusCode (String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder message (String message) {
            this.message = message;
            return this;
        }

        public CreateTaskResponse build () {
            return new CreateTaskResponse (this);
        }
    }

}
