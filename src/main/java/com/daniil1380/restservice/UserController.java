package com.daniil1380.restservice;

import com.daniil1380.restservice.dto.LoggedUser;
import com.daniil1380.restservice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    StoreUserComponent storeUserComponent;

    @PostMapping(value = "/user/login",
            produces = { "application/json" })
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User logged = storeUserComponent.findUserByLogin(user.getLogin());
        if (logged != null) {
            return ResponseEntity.badRequest().body(null);
        }
        var uuid = UUID.randomUUID();
        user.setUuid(uuid.hashCode());
        var loggedUser = new LoggedUser();
        loggedUser.setUuid(user.getUuid());
        loggedUser.setLogin(user.getLogin());
        loggedUser.setPassword(user.getPassword());
        loggedUser.setLastResult(0);
        loggedUser.setStartedTest(-1);
        storeUserComponent.addNewUser(loggedUser);
        return ResponseEntity.ok(user);
    }

}
