package com.example.safedrivev2.exceptions;

public class InexistentUserException extends CredentialsException {

    private String message;

    public InexistentUserException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
