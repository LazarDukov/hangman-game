package com.example.hangman.controller;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;
import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import com.example.hangman.repository.CategoryRepository;
import com.example.hangman.repository.DifficultyRepository;
import com.example.hangman.service.PlayService;
import com.example.hangman.service.UserService;
import com.example.hangman.util.WordSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class PlayController {
    private final PlayService playService;
    private final UserService userService;

    private final WordSession wordSession;

    private final DifficultyRepository difficultyRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PlayController(PlayService playService, UserService userService, WordSession wordSession, DifficultyRepository difficultyRepository, CategoryRepository categoryRepository) {
        this.playService = playService;
        this.userService = userService;
        this.wordSession = wordSession;
        this.difficultyRepository = difficultyRepository;
        this.categoryRepository = categoryRepository;
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
    private String getWordPage(Principal principal, Model model,
                               @RequestParam String difficulty,
                               @RequestParam String category) {
        Difficulty chosenDifficulty = difficultyRepository.findDifficultyByDifficultyEnum(DifficultyEnum.valueOf(difficulty));
        Category chosenCategory = categoryRepository.findCategoryByCategoryEnum(CategoryEnum.valueOf(category));
        List<Word> secretWords = playService.getSecretWords(chosenDifficulty, chosenCategory);
        if (secretWords.size() == 0) {

            String selectionError = "You should choose another combination!";
            model.addAttribute("selectionError", selectionError);
            return "get-word";
        }
        // Check if words in this category are already finished by the logged user
        List<Word> wordListByCategory = playService.getSecretWordsByCategory(chosenCategory);
        List<Word> userWordListByCategory = userService.getUserWordsByCategory(principal, chosenCategory);
        if (wordListByCategory.size() == userWordListByCategory.size()) {
            String userFinishedCategory = "You revealed all the words by this category!";
            model.addAttribute("userFinishedCategory", userFinishedCategory);
            return "get-word";
        }

        Word word = playService.getSecretWord(principal, chosenDifficulty, chosenCategory);


        wordSession.setWord(word.getWord());
        wordSession.setDifficulty(chosenDifficulty);
        wordSession.setCategory(chosenCategory);
        model.addAttribute("currentWord", word.getWord());
        model.addAttribute("descriptionOfWord", word.getDescription());
        return "play";
    }

    @GetMapping("/lost")
    private String getLostPage(Model model) {
        String secretWord = wordSession.getWord();
        model.addAttribute("secretWord", secretWord);
        return "lost";
    }


}
