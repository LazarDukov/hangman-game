package com.example.hangman.service;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.Difficulty;
import com.example.hangman.model.entity.User;
import com.example.hangman.model.entity.Word;
import com.example.hangman.repository.UserRepository;
import com.example.hangman.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class PlayService {
    private final WordRepository wordRepository;
    private final UserRepository userRepository;


    @Autowired
    public PlayService(WordRepository wordRepository, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
    }

    public Word getSecretWord(Principal principal, Difficulty difficulty, Category category) {
        User loggedUser = userRepository.findUserByUsername(principal.getName()).orElse(null);
        List<Word> listOfWords = getSecretWords(difficulty, category);
        Word word = searchSecretWord(getSecretWords(difficulty, category));
        Word wordToGet = word;
        while (loggedUser.getGuessedWords().stream().anyMatch(word1 -> word1.getWord().equals(wordToGet.getWord()))) {
            // TODO: should create a logic for this exception!
            if (loggedUser.getGuessedWords().size() == listOfWords.size()) {
                throw new IllegalStateException("You doesnt have enough words to searching for!");
            }
            word = searchSecretWord(getSecretWords(difficulty, category));
        }
        return wordToGet;
    }

    public Word searchSecretWord(List<Word> secretWords) {
        int min = 0;
        int max = secretWords.size() - 1;
        int randomIndex = (min + (int) (Math.random() * ((max - min) + 1)));
        return secretWords.get(randomIndex);
    }

    public List<Word> getSecretWords(Difficulty difficulty, Category category) {
        return wordRepository.findAllByDifficultyAndCategory(difficulty, category);

    }


    public List<Word> getSecretWordsByCategory(Category category) {
        return wordRepository.findAllByCategory(category);
    }
}
