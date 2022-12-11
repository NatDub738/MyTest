package com.example.mytest;

public class UserLoginModel {
    private String email;
    private String password;

    public UserLoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return email;
    }

    public void setLogin(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
