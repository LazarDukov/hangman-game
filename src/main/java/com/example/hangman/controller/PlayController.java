package com.example.hangman.controller;

import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import com.example.hangman.service.PlayService;
import com.example.hangman.service.UserService;
import com.example.hangman.util.WordSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class PlayController {
    private final PlayService playService;
    private final UserService userService;

    @Autowired
    public PlayController(PlayService playService, UserService userService) {
        this.playService = playService;
        this.userService = userService;
    }

    @Autowired
    public WordSession wordSession;

    @GetMapping("/")
    private String getIndex() {
        return "index";
    }

    @GetMapping("/get-word")
    private String getWord() {
        return "get-word";
    }

    @GetMapping("/play")
    private String getWordPage(Model model, @RequestParam DifficultyEnum difficulty, @RequestParam CategoryEnum category) {
        List<Word> secretWords = playService.getSecretWords(difficulty, category);
        String word = playService.secretWord(secretWords).getWord();
        wordSession.setWord(word);
        model.addAttribute("currentWord", word);
        return "play";
    }

    @GetMapping("/lost")
    private String getLostPage(Model model) {
        String secretWord = wordSession.getWord();
        model.addAttribute("secretWord", secretWord);
        return "lost";
    }
    @GetMapping("/won")
    private String getWonPage(Model model, Principal principal) {
        String secretWord = wordSession.getWord();
        userService.updatePoints(principal);
        model.addAttribute("secretWord", secretWord);
        return "won";
    }

}
