package com.example.hangman.service;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.User;
import com.example.hangman.model.entity.UserRole;
import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.UserRoleEnum;
import com.example.hangman.repository.UserRepository;
import com.example.hangman.repository.UserRoleRepository;
import com.example.hangman.repository.WordRepository;
import com.example.hangman.util.WordSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;
    private final WordRepository wordRepository;


    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, WordRepository wordRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
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
        UserRole userRole = userRoleRepository.findUserRoleByRole(UserRoleEnum.USER);
        return userRepository.findAllByRolesIsOrderByPointsDesc(userRole);

    }

    public void addGuessedWord(Principal principal, String word) {
        User loggedUser = getLoggedUser(principal.getName());
        Word wordToAdd = wordRepository.findFirstByWord(word);
        loggedUser.getGuessedWords().add(wordToAdd);
        userRepository.save(loggedUser);


    }

    public List<Word> getUserWordsByCategory(Principal principal, Category chosenCategory) {
        User loggedUser = getLoggedUser(principal.getName());
        return loggedUser.getGuessedWords().stream().filter(w -> w.getCategory().equals(chosenCategory)).collect(Collectors.toList());
    }

    public boolean checkIsWinner(Principal principal) {
        User loggedUser = getLoggedUser(principal.getName());
        List<Word> allWordsInDb = wordRepository.findAll();
        List<Word> allWordsOfUser = loggedUser.getGuessedWords().stream().toList();
        return allWordsOfUser.size() == allWordsInDb.size();

    }

    public void restart() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            wordRepository.findAll().forEach(user.getGuessedWords()::remove);
            user.setPoints(0);
            userRepository.save(user);
        }


    }
}
