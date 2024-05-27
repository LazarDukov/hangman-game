package com.example.hangman.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "words")
public class Word extends BaseEntity {
    @Column
    private String word;

    @Column
    private String description;

    @OneToOne
    private Category category;
    @OneToOne
    private Difficulty difficulty;


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

    public Category getCategory() {
        return category;
    }

    public Word setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Word setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }
}
