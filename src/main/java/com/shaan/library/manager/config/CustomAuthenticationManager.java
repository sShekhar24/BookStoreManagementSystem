package com.shaan.library.manager.config;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.shaan.library.manager.entities.UserEntity;
import com.shaan.library.manager.exceptions.UserNotFoundException;
import com.shaan.library.manager.services.UserService;

@Component
public class CustomAuthenticationManager implements AuthenticationManager{

	private UserService userService;
	
	public CustomAuthenticationManager(UserService userService) {
		this.userService = userService;
	}
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal().toString();
		UserEntity user = userService.findUserByUsername(username);
		if(user==null) throw new UserNotFoundException();
		return new UsernamePasswordAuthenticationToken(username, user.getPassword(), new ArrayList<>());
	}

}
