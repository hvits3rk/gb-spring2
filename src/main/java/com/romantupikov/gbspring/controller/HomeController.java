package com.romantupikov.gbspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @ModelAttribute("activeController")
    public String activeController() {
        return "home";
    }


    @GetMapping("/")
    public String home() {
        return "index";
    }
}
