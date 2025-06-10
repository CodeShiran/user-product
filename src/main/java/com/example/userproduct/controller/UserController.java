package com.example.userproduct.controller;

import com.example.userproduct.data.User;
import com.example.userproduct.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "user registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        userService.loginUser(user);
        return "user logged in successfully";
    }

    @PostMapping("/logout")
    public String logoutUser(@RequestBody User user) {
        userService.logoutUser(user);
        return "user logged out successfully";
    }
}
