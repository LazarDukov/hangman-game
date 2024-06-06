package com.example.hangman.service;

import com.example.hangman.model.dto.NewWordDTO;
import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;
import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import com.example.hangman.repository.CategoryRepository;
import com.example.hangman.repository.DifficultyRepository;
import com.example.hangman.repository.WordRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final DifficultyRepository difficultyRepository;
    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public WordService(WordRepository wordRepository, DifficultyRepository difficultyRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.wordRepository = wordRepository;
        this.difficultyRepository = difficultyRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public void addNewWord(@Valid NewWordDTO newWordDTO) {
        Word newWord = new Word();
        newWord.setWord(newWordDTO.getWord());
        newWord.setDescription(newWordDTO.getDescription());
        newWord.setDifficulty(findDifficulty(newWordDTO.getWord()));
        newWord.setCategory(findCategory(newWordDTO.getCategory()));
        wordRepository.save(newWord);
    }

    private Difficulty findDifficulty(String newWord) {
        int wordLength = newWord.length();
        if (wordLength <= 5) {
            return difficultyRepository.findDifficultyByDifficultyEnum(DifficultyEnum.EASY);
        } else if (wordLength > 5 && wordLength <= 10) {
            return difficultyRepository.findDifficultyByDifficultyEnum(DifficultyEnum.MEDIUM);
        } else {
            return difficultyRepository.findDifficultyByDifficultyEnum(DifficultyEnum.HARD);
        }

    }

    private Category findCategory(String categoryOfNewWord) {
        return categoryRepository.findCategoryByCategoryEnum(CategoryEnum.valueOf(categoryOfNewWord));
    }

    public List<Word> getAllWords() {
        return this.wordRepository.findAll();
    }


    public void deleteWord(Long id) {
        wordRepository.deleteById(id);

    }

}
