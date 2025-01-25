package com.shaan.library.manager.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shaan.library.manager.entities.UserEntity;
import com.shaan.library.manager.exceptions.UserNotFoundException;
import com.shaan.library.manager.models.AddUserRequest;
import com.shaan.library.manager.models.UpdateUserRequest;
import com.shaan.library.manager.repositories.UserRepository;
import com.shaan.library.manager.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserEntity addNewUser(AddUserRequest addUserRequest) {
		return userRepository.save(UserEntity.builder()
				.email(addUserRequest.getEmail())
				.firstName(addUserRequest.getFirstName())
				.lastName(addUserRequest.getLastName())
				.username(addUserRequest.getUsername())
				.password(passwordEncoder.encode(addUserRequest.getPassword()))
				.build());
	}

	@Override
	public UserEntity updateUser(UpdateUserRequest updateUserRequest) {
		UserEntity user = userRepository
				.findById(updateUserRequest.getUserId())
				.orElseThrow(UserNotFoundException::new);
		user.setFirstName(updateUserRequest.getFirstName());
		user.setLastName(updateUserRequest.getLastName());
		user.setEmail(updateUserRequest.getEmail());
		return userRepository.save(user);
	}

	@Override
	public boolean deleteUserById(long userId) {
		userRepository.findById(userId).ifPresentOrElse(user -> userRepository.delete(user), () -> {
			throw new UserNotFoundException();
		});
		return true;
	}

	@Override
	public List<UserEntity> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<UserEntity> findAllUsersByName(String searchCriteria) {
		if(searchCriteria==null) return new ArrayList<>();
		
		String[] firstLastName = searchCriteria.split(" ", 2);
		
		if(firstLastName.length==0) return new ArrayList<>();
		if(firstLastName.length==1) return userRepository.findAllUserByName(firstLastName[0].toLowerCase(), "");
		if(firstLastName.length==2) return userRepository.findAllUserByName(firstLastName[0].toLowerCase(), firstLastName[1].toLowerCase());
		return new ArrayList<>();
	}

	@Override
	public UserEntity findUserByUsername(String username) {
		return userRepository.findUserEntityByUsername(username).orElseThrow(() -> {throw new UserNotFoundException();});
	}

	@Override
	public UserEntity findUserById(long id) {
		return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}

}
