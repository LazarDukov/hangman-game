package com.example.hangman.repository;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;
import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findAllByDifficultyAndCategory(Difficulty difficultyEnum, Category categoryEnum);
}
