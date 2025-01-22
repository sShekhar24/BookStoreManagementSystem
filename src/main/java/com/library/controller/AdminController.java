package com.library.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {

    @GetMapping("/admin/manage-users")
    public String manageUsers() {
        return "admin/manage-users"; // Manage Users Page
    }

    @GetMapping("/admin/manage-books")
    public String manageBooks() {
        return "admin/manage-books"; // Manage Books Page
    }
}
