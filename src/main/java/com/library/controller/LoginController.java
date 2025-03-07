package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.library.dto.LoginRequestDTO;
import com.library.entity.User;
import com.library.service.UserService;
import com.library.util.TokenUtil;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(@RequestBody LoginRequestDTO loginRequest) {
        if(loginRequest==null || loginRequest.getUsername() == null || 
        		loginRequest.getPassword()==null) throw new IllegalArgumentException();
        User user = userService.findByUsername(loginRequest.getUsername());
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        	throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Invalid Username/password");
        }
        return tokenUtil.generateToken(user);
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "login";  // Redirect to login if not authenticated
        }

        String currentRole = authentication.getAuthorities().toString();
        
        if (currentRole.contains("ROLE_ADMIN")) {
            return "admin/dashboard";
        } else if (currentRole.contains("ROLE_LIBRARIAN")) {
            return "librarian/dashboard";
        } else if (currentRole.contains("ROLE_STUDENT")) {
            return "student/dashboard";
        }

        return "login";  // Default to login page if no matching role
    }
}
