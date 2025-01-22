package com.library.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.library.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {

    private String secretKey = "secret";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))  // 1 day
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
