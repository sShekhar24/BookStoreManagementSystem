package com.library.manager.services;

import com.library.manager.entities.UserEntity;
import com.library.manager.models.LoginRequest;

public interface AuthenticationService {
	
	public String login(LoginRequest loginRequest);
	public void logout();
	public boolean validate();
	public UserEntity getLoggedInUser();

}
