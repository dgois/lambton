package com.example.macstudent.cricketplayerrank.model;

/**
 * Created by macstudent on 2017-12-01.
 */

public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isValid() {
        return isUserNameValid() && isPasswordValid();
    }

    private boolean isUserNameValid() {
        return username != null
                && !username.trim().equals("")
                && username.equals("admin@lambton.com");
    }

    private boolean isPasswordValid() {
        return password != null
                && !password.trim().equals("")
                && password.equals("admin");
    }
}
