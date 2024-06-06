package com.example.hangman;

import com.example.hangman.model.dto.NewWordDTO;
import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;
import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import com.example.hangman.repository.CategoryRepository;
import com.example.hangman.repository.DifficultyRepository;
import com.example.hangman.repository.WordRepository;
import com.example.hangman.service.WordService;
import org.junit.jupiter.api.AfterEach;
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

import static org.mockito.Mockito.*;

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
    @Mock
    private NewWordDTO newWordDTO;
    @Mock
    private Word word;
    private WordService serviceToTest;


    @BeforeEach
    void setUp() {
        Category animals = new Category().setCategoryEnum(CategoryEnum.ANIMALS);
        Difficulty easy = new Difficulty().setDifficultyEnum(DifficultyEnum.EASY);
        newWordDTO = new NewWordDTO();
        newWordDTO.setWord("tiger");
        newWordDTO.setDescription("This animal is running fast and he like to eat meat. It is a big cat");
        newWordDTO.setCategory("ANIMALS");

        when(mockCategoryRepository.findCategoryByCategoryEnum(CategoryEnum.ANIMALS)).thenReturn(animals);
        when(mockDifficultyRepository.findDifficultyByDifficultyEnum(DifficultyEnum.EASY)).thenReturn(easy);
        word = new Word();
        word.setWord(newWordDTO.getWord());
        word.setDescription(newWordDTO.getDescription());
        word.setCategory(animals);
        lenient().when(mockModelMapper.map(newWordDTO, Word.class)).thenReturn(word);

        serviceToTest = new WordService(mockWordRepository, mockDifficultyRepository,
                mockCategoryRepository, mockModelMapper);
    }


    @Test
    void addNewWordTest() {


        serviceToTest.addNewWord(newWordDTO);

        Mockito.verify(mockWordRepository).save(wordToAdd.capture());
        Word capturedWord = wordToAdd.getValue();
        Assertions.assertEquals(word.getWord(), capturedWord.getWord());
        Assertions.assertEquals(word.getDescription(), capturedWord.getDescription());
    }

}
