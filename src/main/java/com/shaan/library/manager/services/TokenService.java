package com.shaan.library.manager.services;

import io.jsonwebtoken.Claims;

public interface TokenService {

	public String generateToken(Claims claims);
	public Object parseToken(String token);
	
}
