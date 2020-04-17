package com.example.demo.controller;

import com.example.demo.model.AccountCredentials;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public User signUp(@RequestBody AccountCredentials accountCredentials){
        //bscrypt password
        accountCredentials.setPassword(bCryptPasswordEncoder.encode(accountCredentials.getPassword()));

        return userService.signUp(accountCredentials);
        //return userService.signUp(user);
    }
}
