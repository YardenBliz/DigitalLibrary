package org.example.digitallibraryclient.model;

public class Response {
    private final String message;
    private final Object data;

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}


