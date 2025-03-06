package com.library.config;

import java.io.IOException;
import java.util.Collection;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.library.entity.Role;
import com.library.entity.User;
import com.library.util.TokenUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private TokenUtil tokenUtil = new TokenUtil();
	private Gson gson = new Gson();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(Strings.isBlank(tokenHeader)) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = tokenHeader.replace("Bearer ", "");
		Claims claims = tokenUtil.parseClaims(token);
		User user = gson.fromJson(claims.get("user").toString(), User.class);
		SecurityContextHolder
		.getContext()
		.setAuthentication(new UsernamePasswordAuthenticationToken(
				user, 
				user.getEmail(),
				getAuths(user)));
		filterChain.doFilter(request, response);
	}

	private Collection<? extends GrantedAuthority> getAuths(User user) {
		return user.getRoles()
				.stream()
				.map(Role::getName)
				.map(SimpleGrantedAuthority::new)
				.toList();
	}

}
