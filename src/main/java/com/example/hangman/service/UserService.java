package com.example.hangman.service;

import com.example.hangman.model.entity.Difficulty;
import com.example.hangman.model.entity.User;
import com.example.hangman.repository.UserRepository;
import com.example.hangman.util.WordSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User getLoggedUser(String username) {
        return this.userRepository.findUserByUsername(username).orElse(null);
    }

    public void updatePoints(Principal principal, WordSession wordSession) {
        User loggedUser = getLoggedUser(principal.getName());
        if (wordSession.getDifficulty().getDifficultyEnum().toString().equals("EASY")) {
            loggedUser.setPoints(loggedUser.getPoints()+100);
        } else if (wordSession.getDifficulty().getDifficultyEnum().toString().equals("MEDIUM")) {
            loggedUser.setPoints(loggedUser.getPoints()+200);
        } else if (wordSession.getDifficulty().getDifficultyEnum().toString().equals("HARD")) {
            loggedUser.setPoints(loggedUser.getPoints()+300);
        }
        userRepository.save(loggedUser);
    }
}
