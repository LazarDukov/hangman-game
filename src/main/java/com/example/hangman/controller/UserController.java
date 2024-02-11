package com.example.hangman.controller;

import com.example.hangman.service.UserService;
import com.example.hangman.util.WordSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final WordSession wordSession;

    public UserController(UserService userService, WordSession wordSession) {
        this.userService = userService;
        this.wordSession = wordSession;
    }

    @GetMapping("/won")
    private String getWonPage(Model model, Principal principal) {
        String secretWord = wordSession.getWord();
        userService.updatePoints(principal, wordSession);
        model.addAttribute("secretWord", secretWord);
        return "won";
    }
}