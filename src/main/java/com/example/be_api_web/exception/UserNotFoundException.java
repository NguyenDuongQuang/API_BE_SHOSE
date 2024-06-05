package com.example.be_api_web.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User with this username not found in database!!");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
