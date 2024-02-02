package com.example.hangman.controller;

import com.example.hangman.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/won")
    private String getWonPage(Principal principal) {
        userService.updatePoints(principal);
        return "/won";
    }

    @GetMapping("/lost")
    private String getLostPage() {
        return "lost";
    }
}
