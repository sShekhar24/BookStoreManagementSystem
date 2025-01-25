package com.shaan.library.manager.services;

import com.shaan.library.manager.entities.UserEntity;
import com.shaan.library.manager.models.LoginRequest;

public interface AuthenticationService {
	
	public String login(LoginRequest loginRequest);
	public void logout();
	public boolean validate();
	public UserEntity getLoggedInUser();

}
