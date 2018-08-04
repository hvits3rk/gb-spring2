package com.romantupikov.gbspring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @ModelAttribute("activeController")
    public String activeController() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}
