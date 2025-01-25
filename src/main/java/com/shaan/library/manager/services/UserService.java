package com.shaan.library.manager.services;

import java.util.List;

import com.shaan.library.manager.entities.UserEntity;
import com.shaan.library.manager.models.AddUserRequest;
import com.shaan.library.manager.models.UpdateUserRequest;

public interface UserService {
	
	public UserEntity addNewUser(AddUserRequest addUserRequest);
	public UserEntity updateUser(UpdateUserRequest updateUserRequest);
	public boolean deleteUserById(long userId);
	public List<UserEntity> findAllUsers();
	public List<UserEntity> findAllUsersByName(String searchCriteria);
	public UserEntity findUserByUsername(String username);
	public UserEntity findUserById(long id);

}
