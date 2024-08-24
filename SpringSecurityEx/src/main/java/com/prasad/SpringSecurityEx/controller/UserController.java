package com.prasad.SpringSecurityEx.controller;

import com.prasad.SpringSecurityEx.model.Users;
import com.prasad.SpringSecurityEx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
          return service.register(user);
    }
}
