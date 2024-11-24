package com.tasktracker.model.response;


public class DeleteTaskResponse {
    String statusCode;
    String message;


    DeleteTaskResponse (Builder builder) {
        this.statusCode = builder.statusCode;
        this.message = builder.message;
    }

    public String getStatusCode () {
        return statusCode;
    }

    public String getMessage () {
        return message;
    }

    public static class Builder {
        private String statusCode;
        private String message;

        public Builder statusCode (String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder message (String message) {
            this.message = message;
            return this;
        }

        public DeleteTaskResponse build () {
            return new DeleteTaskResponse (this);
        }
    }
}
