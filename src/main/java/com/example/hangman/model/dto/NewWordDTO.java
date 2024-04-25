package com.example.hangman.model.dto;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class NewWordDTO {
    @NotNull(message = "This field cannot be empty!")
    @Size(min = 2, max = 20, message = "Size must be between 2 and 20 symbols!")
    private String word;
    @NotNull(message = "This field cannot be empty!")
    @Size(min = 2, max = 20, message = "Size must be between 20 and 150 symbols!")
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
