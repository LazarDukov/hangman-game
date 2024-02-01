package com.example.hangman.model.entity;

import com.example.hangman.model.enums.DifficultyEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "difficulty")
public class Difficulty extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column
    private DifficultyEnum difficultyEnum;

    public Difficulty() {
    }

    public DifficultyEnum getDifficultyEnum() {
        return difficultyEnum;
    }

    public Difficulty setDifficultyEnum(DifficultyEnum difficultyEnum) {
        this.difficultyEnum = difficultyEnum;
        return this;
    }
}
