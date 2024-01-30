package com.example.hangman.controller;

import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import com.example.hangman.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlayController {
    private final PlayService playService;

    @Autowired
    public PlayController(PlayService playService) {
        this.playService = playService;
    }

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
        model.addAttribute("currentWord", word);
        return "play";
    }

}
