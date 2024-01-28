package com.example.hangman.service;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.entity.User;
import com.example.hangman.model.entity.UserRole;
import com.example.hangman.model.entity.Word;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultEnum;
import com.example.hangman.model.enums.UserRoleEnum;
import com.example.hangman.repository.CategoryRepository;
import com.example.hangman.repository.UserRepository;
import com.example.hangman.repository.UserRoleRepository;
import com.example.hangman.repository.WordRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InitService {
    private final UserRoleRepository userRoleRepository;
    private final WordRepository wordRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitService(UserRoleRepository userRoleRepository, WordRepository wordRepository, CategoryRepository categoryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.wordRepository = wordRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void Init() {
        if (userRoleRepository.count() == 0) {
            initRoles();
        }
        if (categoryRepository.count() == 0) {
            initCategories();
        }
        if (userRepository.count() == 0) {
            initUsers();
        }
        if (wordRepository.count() == 0) {
            initWords();
        }
    }


    private void initRoles() {
        UserRole user = new UserRole().setRole(UserRoleEnum.USER);
        UserRole admin = new UserRole().setRole(UserRoleEnum.ADMIN);

        userRoleRepository.save(user);
        userRoleRepository.save(admin);
    }

    private void initCategories() {
        Category history = new Category().setCategory(CategoryEnum.HISTORY);
        Category geography = new Category().setCategory(CategoryEnum.GEOGRAPHY);
        Category animals = new Category().setCategory(CategoryEnum.ANIMALS);
        categoryRepository.save(history);
        categoryRepository.save(geography);
        categoryRepository.save(animals);
    }

    private void initUsers() {
        User gamer = new User();
        gamer.setUsername("gamer");
        gamer.setPassword(passwordEncoder.encode("12345"));
        gamer.setFirstName("gamer");
        gamer.setLastName("gamerov");
        gamer.setEmail("gamer@abv.bg");
        gamer.setRoles(new ArrayList<>());
        gamer.getRoles().add(userRoleRepository.findUserRoleByRole(UserRoleEnum.USER));
        userRepository.save(gamer);
    }

    private void initWords() {
        Word tiger = new Word();
        tiger.setWord("tiger");
        tiger.setCategory(CategoryEnum.ANIMALS);
        tiger.setDescription("An animal which is from the cats family and can run very fast.");
        tiger.setDifficult(DifficultEnum.EASY);

        Word newYork = new Word();
        newYork.setWord("new york");
        newYork.setCategory(CategoryEnum.GEOGRAPHY);
        newYork.setDescription("A very big city which is located somewhere in USA.");
        newYork.setDifficult(DifficultEnum.EASY);


        wordRepository.save(tiger);
        wordRepository.save(newYork);
    }


}
