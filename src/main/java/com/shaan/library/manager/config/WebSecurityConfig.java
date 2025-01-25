package com.shaan.library.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

	private JwtTokenAuthenticationFilter authenticationFilter;
	
	public WebSecurityConfig(JwtTokenAuthenticationFilter authenticationFilter) {
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager, AuthenticationProvider authenticationProvider) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(req -> req
					.requestMatchers("/error*", "/api/auth/login*").permitAll()
					.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
					.anyRequest().authenticated()
				)
				.formLogin(formLoginCustomizer -> formLoginCustomizer
						.disable())
				.authenticationManager(authenticationManager)
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsDatasource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
