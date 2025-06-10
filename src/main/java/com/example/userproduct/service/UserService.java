package com.example.userproduct.service;

import com.example.userproduct.data.User;
import com.example.userproduct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User RegisterUser(User user) {
        // Logic to register a user
        return userRepository.save(user);
    }

    public User LoginUser(User user) {
        // Logic to login a user
        return userRepository.findByName(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with name: " + user.getUsername()));
    }
}
