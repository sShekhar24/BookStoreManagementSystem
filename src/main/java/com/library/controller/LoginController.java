package com.library.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // The login page view
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
