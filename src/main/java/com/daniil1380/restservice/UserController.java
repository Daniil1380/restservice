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
            if (!logged.getPassword().equals(user.getPassword())) {
                System.out.println("Такой пользователь уже зарегистрирован, пароль неверен. Введите верный пароль");
                return ResponseEntity.badRequest().body(null);
            }
            System.out.println("Пользователь зарегистрирован. Пароль введен верно!");
            user.setUuid(logged.getUuid());
        }
        else {
            var uuid = UUID.randomUUID();
            user.setUuid((long) uuid.hashCode());
            var loggedUser = new LoggedUser();
            loggedUser.setUuid(user.getUuid());
            loggedUser.setLogin(user.getLogin());
            loggedUser.setPassword(user.getPassword());
            loggedUser.setLastResult(0L);
            loggedUser.setStartedTest(-1L);
            storeUserComponent.addNewUser(loggedUser);
            System.out.println("Пользователь не зарегистрирован, создан новый пользователь!");
        }
        return ResponseEntity.ok(user);
    }

}
