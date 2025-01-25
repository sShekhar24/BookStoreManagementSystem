package com.shaan.library.manager.controllers;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shaan.library.manager.entities.UserEntity;
import com.shaan.library.manager.models.AddUserRequest;
import com.shaan.library.manager.models.UpdateUserRequest;
import com.shaan.library.manager.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserEntity> findAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/id/{id}")
	public UserEntity findUserById(@PathVariable long id) {
		return userService.findUserById(id);
	}

	@GetMapping("/username")
	public UserEntity findUserByUsername(@RequestParam("username") String username) {
		return userService.findUserByUsername(username);
	}
	
	@GetMapping("/name")
	public List<UserEntity> findUserByName(@RequestParam("criteria") String searchCriteria){
		return userService.findAllUsersByName(searchCriteria);
	}

	@PostMapping
	public UserEntity addNewUser(@Validated @RequestBody AddUserRequest addUserRequest) {
		return userService.addNewUser(addUserRequest);
	}

	@PutMapping
	public UserEntity updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
		return userService.updateUser(updateUserRequest);
	}

	@DeleteMapping("/id/{id}")
	public boolean deleteUserById(@PathVariable long id) {
		return userService.deleteUserById(id);
	}

}
