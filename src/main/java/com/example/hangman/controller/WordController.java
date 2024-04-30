package com.example.hangman.controller;

import com.example.hangman.model.dto.NewWordDTO;
import com.example.hangman.model.entity.Word;
import com.example.hangman.service.WordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WordController {
    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/manage-words")
    private String manageWordsPage(Model model) {
        List<Word> allWords = wordService.getAllWords();
        model.addAttribute("allWords", allWords);
        return "manage-words";
    }

    @ModelAttribute("newWordDTO")
    public NewWordDTO addNewWordDTO() {
        return new NewWordDTO();
    }

    @GetMapping("/add-word")
    private String getAddRolePage() {
        return "add-word";
    }

    @PostMapping("/add-word")
    private String postAddRolePage(@Valid NewWordDTO newWordDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newWordDTO", newWordDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newWordDTO", bindingResult);
            return "redirect:/add-word";
        }
        wordService.addNewWord(newWordDTO);
        return "redirect:/";
    }

    @GetMapping("/manage-words/{id}")
    public String deleteWordById(@PathVariable("id") Long id) {
        // Word word = wordService.getWordById(id);
        wordService.deleteWord(id);
        return "redirect:/manage-words";

    }

}
