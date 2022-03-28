package com.daniil1380.restservice;

import com.daniil1380.restservice.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @RequestMapping(value = "/user/login",
            produces = { "application/json" },
            method = RequestMethod.POST)
    User AddUser(@RequestBody User user) {
        return user;
    }

}
