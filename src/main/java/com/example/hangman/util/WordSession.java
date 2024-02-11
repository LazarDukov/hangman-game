package com.example.hangman.util;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class WordSession {
    private String word;
    private Difficulty difficulty;
    private Category category;

    public WordSession() {
    }

    public String getWord() {
        return word;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Category getCategory() {
        return category;
    }

    public WordSession setWord(String word) {
        this.word = word;
        return this;
    }

    public WordSession setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public WordSession setCategory(Category category) {
        this.category = category;
        return this;
    }
}
