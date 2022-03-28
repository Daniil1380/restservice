package com.daniil1380.restservice.dto;

public class User {

    private Integer uuid;

    private String login;

    private String password;

    public Integer getUuid() {
        return uuid;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Integer uuid, String login, String password) {
        this.uuid = uuid;
        this.login = login;
        this.password = password;
    }

    public User() {
    }
}
