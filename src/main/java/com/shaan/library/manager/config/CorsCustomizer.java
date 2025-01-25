package com.shaan.library.manager.config;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsCustomizer extends OncePerRequestFilter{
	
	private static final List<String> ALLOWED_ORIGINS = List.of(
			"http://localhost:5501",
			"localhost:5501",
			"localhost",
			"http://localhost:5501/",
			"http://127.0.0.1:5501",
			"127.0.0.1:5501",
			"127.0.0.1",
			"http://127.0.0.1:5501/"
	);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS));
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD));
		if(ALLOWED_ORIGINS.contains(getOrigin(request))) {
			response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader(HttpHeaders.ORIGIN));
		}
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		filterChain.doFilter(request, response);
	}

	private String getOrigin(HttpServletRequest request) {
		String origin = request.getHeader(HttpHeaders.ORIGIN);
		if(!Strings.isBlank(origin)) return origin;
		return request.getHeader(HttpHeaders.HOST);
	}

}
