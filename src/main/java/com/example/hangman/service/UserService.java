package com.example.hangman.service;

import com.example.hangman.model.entity.User;
import com.example.hangman.repository.UserRepository;
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

    public void updatePoints(Principal principal) {
        User loggedUser = getLoggedUser(principal.getName());
        loggedUser.setPoints(loggedUser.getPoints() + 1);
    }
}
