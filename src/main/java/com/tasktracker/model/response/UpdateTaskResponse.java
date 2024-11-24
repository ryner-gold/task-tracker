package com.tasktracker.model.response;

import com.tasktracker.model.TaskList;

public class UpdateTaskResponse extends TaskResponse {

    public UpdateTaskResponse (UpdateTaskResponse.Builder builder) {
        super (builder.taskList,
               builder.statusCode,
               builder.message);

    }

    public static class Builder {
        private TaskList taskList;
        private String statusCode;
        private String message;

        public UpdateTaskResponse.Builder taskList (TaskList taskList) {
            this.taskList = taskList;
            return this;
        }

        public UpdateTaskResponse.Builder statusCode (String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public UpdateTaskResponse.Builder message (String message) {
            this.message = message;
            return this;
        }

        public UpdateTaskResponse build () {
            return new UpdateTaskResponse (this);
        }
    }
}
