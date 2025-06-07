package com.security.demo.controller;

import com.security.demo.entities.User;
import com.security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class APIController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/")
    public String getResource(){
        return "resource";
    }

    // Handle registration form POST
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/home";
        }
        User user=new User();
        user.setEmail(username);
        user.setPassword(passwordEncoder.encode(password));
        user = userService.createUser(user);
        if (user.getId()==null) {
            // Redirect back with error query param on failure
            return "redirect:/register.html?error=true";
        }

        // Redirect to login page on success
        return "redirect:/login.html";
    }
}
