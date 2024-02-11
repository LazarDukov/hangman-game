package com.example.hangman.controller;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;
import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import com.example.hangman.service.PlayService;
import com.example.hangman.util.WordSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlayController {
    private final PlayService playService;

    private final WordSession wordSession;

    @Autowired
    public PlayController(PlayService playService, WordSession wordSession) {
        this.playService = playService;
        this.wordSession = wordSession;
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
        wordSession.setWord(word);
        wordSession.setDifficulty(new Difficulty().setDifficultyEnum(difficulty));
        wordSession.setCategory(new Category().setCategoryEnum(category));
        model.addAttribute("currentWord", word);
        return "play";
    }

    @GetMapping("/lost")
    private String getLostPage(Model model) {
        String secretWord = wordSession.getWord();
        model.addAttribute("secretWord", secretWord);
        return "lost";
    }


}
