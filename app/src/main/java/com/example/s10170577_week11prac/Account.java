package com.example.s10170577_week11prac;

public class Account {
    private String username;
    private String password;

    public Account(String user, String pass){
        username = user;
        password = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
