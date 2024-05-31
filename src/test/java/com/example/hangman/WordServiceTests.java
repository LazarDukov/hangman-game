package com.example.hangman;

import com.example.hangman.model.dto.NewWordDTO;
import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;
import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import com.example.hangman.model.enums.UserRoleEnum;
import com.example.hangman.repository.CategoryRepository;
import com.example.hangman.repository.DifficultyRepository;
import com.example.hangman.repository.WordRepository;
import com.example.hangman.service.WordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WordServiceTests {

    @Mock
    private WordRepository mockWordRepository;

    @Mock
    private DifficultyRepository mockDifficultyRepository;
    @Mock
    private CategoryRepository mockCategoryRepository;
    @Mock
    private ModelMapper mockModelMapper;

    @Captor
    private ArgumentCaptor<Word> wordToAdd;

    private WordService serviceToTest;

    @BeforeEach
    void setUp() {
        Category animals = new Category().setCategoryEnum(CategoryEnum.ANIMALS);
        mockCategoryRepository.save(animals);
        Difficulty easy = new Difficulty().setDifficultyEnum(DifficultyEnum.EASY);
        mockDifficultyRepository.save(easy);


        serviceToTest = new WordService(mockWordRepository, mockDifficultyRepository,
                mockCategoryRepository, mockModelMapper);
    }

    @Test
    void addNewWordTest() {
        NewWordDTO wordTest = new NewWordDTO();
        wordTest.setWord("tiger");
        wordTest.setDescription("This animal is running fast and he like to eat meat. It is a big cat");
        wordTest.setCategory("ANIMALS");


        serviceToTest.addNewWord(wordTest);

        Mockito.verify(mockWordRepository).save(wordToAdd.capture());
        Word capturedWord = wordToAdd.getValue();
        Assertions.assertEquals(wordTest.getWord(), capturedWord.getWord());
        Assertions.assertEquals(wordTest.getDescription(), capturedWord.getDescription());
    }

    @Test
    void getAllWords() {
    }

    @Test
    void getWordById() {
    }

    @Test
    void deleteWord() {
    }
}
