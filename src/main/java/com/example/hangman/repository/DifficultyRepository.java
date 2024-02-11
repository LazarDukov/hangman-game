package com.example.hangman.repository;

import com.example.hangman.model.entity.Difficulty;
import com.example.hangman.model.enums.DifficultyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {
    Difficulty findDifficultyByDifficultyEnum(DifficultyEnum difficultyEnum);
}
