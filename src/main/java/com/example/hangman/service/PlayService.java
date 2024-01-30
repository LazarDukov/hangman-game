package com.example.hangman.service;

import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import com.example.hangman.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayService {
    private final WordRepository wordRepository;


    @Autowired
    public PlayService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;

    }

    public Word secretWord(List<Word> secretWords) {
        int min = 0;
        int max = secretWords.size() - 1;
        int randomIndex = (min + (int) (Math.random() * ((max - min) + 1)));
        return secretWords.get(randomIndex);
    }

    public List<Word> getSecretWords(DifficultyEnum difficulty, CategoryEnum category) {
        return wordRepository.findAllByDifficultyAndCategory(difficulty, category);

    }
}
