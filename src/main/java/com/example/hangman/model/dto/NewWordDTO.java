package com.example.hangman.model.dto;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;


public class NewWordDTO {
    private String word;
    private String description;
    private String category;
    private String difficulty;

    public NewWordDTO() {
    }

    public String getWord() {
        return word;
    }

    public NewWordDTO setWord(String word) {
        this.word = word;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewWordDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public NewWordDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public NewWordDTO setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        return this;
    }
}
