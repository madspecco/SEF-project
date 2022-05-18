package com.example.safedrivev2.exceptions;

public class UserPasswordInvalidException extends CredentialsException {

    private String message;

    public UserPasswordInvalidException(String message) {
        super(message);
        this.message=message;
    }

    public String getMessage() {

        return message;
    }


}
