package com.daniil1380.restservice.dto;

import java.util.List;

public class User {

    private Long uuid;

    private String login;

    private String password;

    public Long getUuid() {
        return uuid;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Long uuid, String login, String password) {
        this.uuid = uuid;
        this.login = login;
        this.password = password;
    }

    public User() {
    }

}
