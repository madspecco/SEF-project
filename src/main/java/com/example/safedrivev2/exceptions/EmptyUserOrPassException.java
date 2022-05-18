package com.example.safedrivev2.exceptions;

public class EmptyUserOrPassException extends CredentialsException {

    private String message;

    public EmptyUserOrPassException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;

    }
}
