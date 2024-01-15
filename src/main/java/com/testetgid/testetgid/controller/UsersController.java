package com.testetgid.testetgid.controller;

import org.springframework.web.bind.annotation.RestController;

import com.testetgid.testetgid.dto.UserDTO;
import com.testetgid.testetgid.model.User;
import com.testetgid.testetgid.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listUsers() {
        return userService.listUsers();
    }
    
    
    @PostMapping("/sign-up")
    public String createUser(@RequestBody UserDTO user) {

        userService.createUser(user);
        
        return "Ok";
    }
}
