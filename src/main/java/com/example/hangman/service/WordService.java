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
        newWord.setDifficulty(findDifficulty(newWordDTO.getDifficulty()));
        newWord.setCategory(findCategory(newWordDTO.getCategory()));
        wordRepository.save(newWord);
    }

    private Difficulty findDifficulty(String difficultyOfNewWord) {
        return difficultyRepository.findDifficultyByDifficultyEnum(DifficultyEnum.valueOf(difficultyOfNewWord));
    }

    private Category findCategory(String categoryOfNewWord) {
        return categoryRepository.findCategoryByCategoryEnum(CategoryEnum.valueOf(categoryOfNewWord));
    }
}
