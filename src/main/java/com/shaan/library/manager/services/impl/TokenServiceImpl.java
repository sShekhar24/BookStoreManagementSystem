package com.shaan.library.manager.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shaan.library.manager.services.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenServiceImpl implements TokenService{

	@Value("${jwt.secret.key}")
	private String privateKey;
	
	@Value("${jwt.expiration.duration}")
	private int expirationDuration;
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@Override
	public String generateToken(Claims claims) {
		long currentTime = System.currentTimeMillis();
		return Jwts
				.builder()
				.claims(claims)
				.expiration(new Date(currentTime+expirationDuration))
				.issuedAt(new Date(currentTime))
				.issuer(applicationName)
				.notBefore(new Date(currentTime))
				.signWith(Keys.hmacShaKeyFor(privateKey.getBytes()))
				.subject(applicationName)
				.compact();
	}

	@Override
	public Object parseToken(String token) {
		return Jwts
				.parser()
				.verifyWith(Keys.hmacShaKeyFor(privateKey.getBytes()))
				.build()
				.parse(token).getPayload().toString();
	}
	
}
