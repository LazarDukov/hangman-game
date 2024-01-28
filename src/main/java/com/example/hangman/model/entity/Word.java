package com.example.hangman.model.entity;

import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "words")
public class Word extends BaseEntity {
    @Column
    private String word;

    @Column
    private String description;

    @Enumerated
    private CategoryEnum category;

    @Enumerated
    private DifficultEnum difficult;

    public Word() {
    }

    public String getWord() {
        return word;
    }

    public Word setWord(String word) {
        this.word = word;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Word setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public Word setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public DifficultEnum getDifficult() {
        return difficult;
    }

    public Word setDifficult(DifficultEnum difficult) {
        this.difficult = difficult;
        return this;
    }
}
