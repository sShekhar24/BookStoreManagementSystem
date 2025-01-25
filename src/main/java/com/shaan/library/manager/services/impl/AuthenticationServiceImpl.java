package com.shaan.library.manager.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shaan.library.manager.entities.UserEntity;
import com.shaan.library.manager.exceptions.UserNotFoundException;
import com.shaan.library.manager.models.LoginRequest;
import com.shaan.library.manager.repositories.UserRepository;
import com.shaan.library.manager.services.AuthenticationService;
import com.shaan.library.manager.services.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	private UserRepository userRepository;
	private TokenService tokenService;
	
	public AuthenticationServiceImpl(UserRepository userRepository, TokenService tokenService) {
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}

	@Override
	public String login(LoginRequest loginRequest) {
		Optional<UserEntity> optionalUser = userRepository.findUserEntityByUsername(loginRequest.getUsername());
		if(optionalUser.isEmpty()) throw new UserNotFoundException();
		return tokenService.generateToken(getClaims(optionalUser.get()));
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserEntity getLoggedInUser() {
		// TODO Auto-generated method stub
		return null;
	}

	private Claims getClaims(UserEntity userEntity) {
		ClaimsBuilder claims = Jwts.claims();
		claims.add("username", userEntity.getUsername());
		return claims.build();
	}
	
}
