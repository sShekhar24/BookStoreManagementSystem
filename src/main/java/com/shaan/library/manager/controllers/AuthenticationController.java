package com.shaan.library.manager.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaan.library.manager.entities.UserEntity;
import com.shaan.library.manager.models.LoginRequest;
import com.shaan.library.manager.services.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	private AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/login")
	public String login(@Validated @RequestBody LoginRequest loginRequest) {
		return authenticationService.login(loginRequest);
	}
	
	@GetMapping("/logout")
	public void logout() {
		authenticationService.logout();
	}
	
	@GetMapping("/validate")
	public boolean validateLogin() {
		return authenticationService.validate();
	}
	
	@GetMapping("/user")
	public UserEntity loggedInUser() {
		return authenticationService.getLoggedInUser();
	}
	
}
