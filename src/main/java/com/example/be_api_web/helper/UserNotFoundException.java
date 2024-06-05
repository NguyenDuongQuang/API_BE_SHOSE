package com.example.be_api_web.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User with this username not found in database!!");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

}