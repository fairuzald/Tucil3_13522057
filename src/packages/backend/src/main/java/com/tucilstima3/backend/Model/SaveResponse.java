package com.tucilstima3.backend.Model;

public class SaveResponse {
    private String message;

    public SaveResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
