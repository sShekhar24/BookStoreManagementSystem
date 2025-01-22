package com.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.library.security.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (enable as needed)
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/books/view").permitAll() // Allow guests to view books
				.requestMatchers("/admin/**").hasRole("ADMIN") // Admin access
				.requestMatchers("/librarian/**").hasRole("LIBRARIAN") // Librarian access
				.requestMatchers("/student/**").hasRole("STUDENT") // Student access
				.requestMatchers("/api/users/**").hasAnyRole("ADMIN", "USER") // Admin/User access
				.anyRequest().authenticated() // Authenticate all other requests
				)
		.formLogin(form -> form
				.loginPage("/login") // Custom login page
				.permitAll()
				)
		.logout(logout -> logout
				.logoutUrl("/logout") // Custom logout endpoint
				.permitAll()
				);

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder =
				http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder
		.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
