package com.library.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenUtil {

    private String secretKey = "QW51YmhhdlNoYXJtYUxpdmVzSW5QYXRuYXNldFN1YmplY3QodXNlci5nZXRFbWFpbCgpKXNldEV4cGlyYXRpb24obmV3IERhdGUoU3lzdGVtLmN1cnJlbnRUaW1lTWlsbGlzKCkgKyA4NjQwMDAwMCkpc2lnbldpdGgoS2V5cy5obWFjU2hhS2V5Rm9yKG51bGwpLCBTaWduYXR1cmVBbGdvcml0aG0uSFM1MTIp";
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String generateToken(User user) {
    	return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .claim("email", user.getEmail())
                .claim("user", gson.toJson(user))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS512)
                .compact();
      
    }
    
    public Claims parseClaims(String token){
    	return Jwts.parserBuilder()
    			.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
    			.build()
    			.parseClaimsJws(token)
    			.getBody();
    }
}
