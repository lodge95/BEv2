package com.mybizexchange.controller;

import com.mybizexchange.service.UserService;
import com.mybizexchange.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/register")
    public String registerForm(Model m){
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        // Minimal validation; in real app check duplicates, email confirmation, etc.
        userService.register(user.getUsername(), user.getPasswordHash(), user.getEmail(), user.getDisplayName());
        return "redirect:/login";
    }
}
