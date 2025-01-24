package com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.entity.User;
import com.bookstore.repository.UserRepository;
import com.bookstore.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method to register a user
    public void register(UserDTO userDTO) {
        // Convert DTO to entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt the password

        // Save user in the database
        userRepository.save(user);
    }

    // Method to authenticate a user
    public boolean authenticate(UserDTO userDTO) {
        // Fetch the user from the database based on username
        User user = userRepository.findByUsername(userDTO.getUsername());

        // Check if the user exists and the password matches
        if (user != null && passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return true;
        }
        return false;
    }
}
