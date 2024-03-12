package com.example.hangman.service;

import com.example.hangman.model.entity.User;
import com.example.hangman.model.entity.Word;
import com.example.hangman.repository.UserRepository;
import com.example.hangman.repository.WordRepository;
import com.example.hangman.util.WordSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WordRepository wordRepository;


    @Autowired
    public UserService(UserRepository userRepository, WordRepository wordRepository) {
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
    }

    private User getLoggedUser(String username) {
        return this.userRepository.findUserByUsername(username).orElse(null);
    }

    public void updatePoints(Principal principal, WordSession wordSession) {
        User loggedUser = getLoggedUser(principal.getName());
        switch (wordSession.getDifficulty().getDifficultyEnum().toString()) {
            case "EASY":
                loggedUser.setPoints(loggedUser.getPoints() + 100);
                break;
            case "MEDIUM":
                loggedUser.setPoints(loggedUser.getPoints() + 200);
                break;
            case "HARD":
                loggedUser.setPoints(loggedUser.getPoints() + 300);
                break;
        }
        userRepository.save(loggedUser);
    }

    public List<User> getRanking() {
        return userRepository.findAllByOrderByPointsDesc();

    }

    public void addGuessedWord(Principal principal, String word) {
        User loggedUser = getLoggedUser(principal.getName());
        Word wordToAdd = wordRepository.findFirstByWord(word);
        loggedUser.getGuessedWords().add(wordToAdd);
        userRepository.save(loggedUser);


    }
}
