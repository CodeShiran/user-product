package com.example.userproduct.service;

import com.example.userproduct.data.User;
import com.example.userproduct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Logic to register a user
        return userRepository.save(user);
    }

    public User loginUser(User user) {
        // Logic to login a user
        return userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with name: " + user.getUsername()));
    }

    public void logoutUser(User user) {
        // Logic to logout a user
        // In a real application, you might invalidate the session or token here
        System.out.println("User " + user.getUsername() + " logged out successfully.");
    }
}
