package com.daniil1380.restservice;

import com.daniil1380.restservice.dto.LoggedUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreUserComponent {

    private final List<LoggedUser> loggedUsers = new ArrayList<>();

    public LoggedUser findUserByUuid(Long uuid) {
        for (LoggedUser loggedUser : loggedUsers) {
            if (loggedUser.getUuid().equals(uuid)) {
                return loggedUser;
            }
        }
        return null;
    }

    public LoggedUser findUserByLogin(String login) {
        for (LoggedUser loggedUser : loggedUsers) {
            if (loggedUser.getLogin().equals(login)) {
                return loggedUser;
            }
        }
        return null;
    }

    public void addNewUser(LoggedUser loggedUser) {
        loggedUsers.add(loggedUser);
    }
}
