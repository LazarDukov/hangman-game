package com.example.hangman.controller;

import com.example.hangman.model.entity.User;
import com.example.hangman.service.UserService;
import com.example.hangman.util.WordSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

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
        userService.addGuessedWord(principal, secretWord);
        model.addAttribute("secretWord", secretWord);
        if (userService.checkIsWinner(principal)) {
            String winnerMsg = "YOU ARE THE WINNER!";
            model.addAttribute("youAreWinner", winnerMsg);
            userService.restart();
            return "won";
        }
        return "won";
    }

    @GetMapping("/ranking")
    private String getRankingPage(Model model) {
        List<User> ranking = userService.getRanking();
        model.addAttribute("ranking", ranking);
        return "ranking";
    }

}
