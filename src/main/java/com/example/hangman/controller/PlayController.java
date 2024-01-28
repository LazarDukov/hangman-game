package com.example.hangman.controller;

import com.example.hangman.model.entity.Word;
import com.example.hangman.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/play")
    private String getPlayPage(Model model) {
        String word = playService.getWord().getWord();
        model.addAttribute("currentWord", word);
        return "play";
    }
}
