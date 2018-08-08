package com.romantupikov.gbspring.security.controller;

import com.romantupikov.gbspring.security.entity.User;
import com.romantupikov.gbspring.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("activeController")
    public String activeController() {
        return "register";
    }

    @GetMapping("/register")
    public String registerForm(final User user) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid final User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        // TODO: create some sort of confirmation to enable account
        user.setEnabled(true);

        userService.register(user);

        return "redirect:login";
    }
}
