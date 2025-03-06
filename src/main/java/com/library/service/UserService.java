package com.library.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.entity.User;
import com.library.repository.RoleRepository;
import com.library.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);  // Pagination logic
    }

	public User findByUsername(String username) {
		return userRepository.findByEmail(username).orElseThrow();
	}

	public User registerUser(User user) {
		user.setRoles(new HashSet<>(roleRepository.saveAll(user.getRoles())));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}
	