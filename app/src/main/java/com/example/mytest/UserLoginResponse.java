package com.example.mytest;

public class UserLoginResponse {
    private String id;
    private String login;
    private String nameNick;
    private String avatar;
    private String token;

    public UserLoginResponse(String id, String login, String nameNick, String avatar, String token) {
        this.id = id;
        this.login = login;
        this.nameNick = nameNick;
        this.avatar = avatar;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNameNick() {
        return nameNick;
    }

    public void setNameNick(String nameNick) {
        this.nameNick = nameNick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
