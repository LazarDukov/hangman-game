package com.example.hangman.service;

import com.example.hangman.model.entity.*;
import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.DifficultyEnum;
import com.example.hangman.model.enums.UserRoleEnum;
import com.example.hangman.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class InitService {
    private final UserRoleRepository userRoleRepository;
    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;
    private final DifficultyRepository difficultyRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitService(UserRoleRepository userRoleRepository, CategoryRepository categoryRepository, DifficultyRepository difficultyRepository, CategoryRepository categoryRepository1, UserRepository userRepository, DifficultyRepository difficultyRepository1, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.difficultyRepository = difficultyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void Init() {
        if (userRoleRepository.count() == 0) {
            initCategories();
        }
        if (difficultyRepository.count() == 0) {
            initDifficulty();
        }
        if (userRoleRepository.count() == 0) {
            initUserRoles();
        }

        if (userRepository.count() == 0) {
            initUsers();
        }

    }

    private void initDifficulty() {
        Difficulty easy = new Difficulty();
        easy.setDifficultyEnum(DifficultyEnum.EASY);
        difficultyRepository.save(easy);
        Difficulty medium = new Difficulty();
        medium.setDifficultyEnum(DifficultyEnum.MEDIUM);
        difficultyRepository.save(medium);
        Difficulty hard = new Difficulty();
        hard.setDifficultyEnum(DifficultyEnum.HARD);
        difficultyRepository.save(hard);
    }

    private void initCategories() {
        Category history = new Category();
        history.setCategoryEnum(CategoryEnum.HISTORY);
        categoryRepository.save(history);
        Category geography = new Category();
        geography.setCategoryEnum(CategoryEnum.GEOGRAPHY);
        categoryRepository.save(geography);
        Category animals = new Category();
        animals.setCategoryEnum(CategoryEnum.ANIMALS);
        categoryRepository.save(animals);
    }

    private void initUserRoles() {
        UserRole admin = new UserRole();
        admin.setRole(UserRoleEnum.ADMIN);
        userRoleRepository.save(admin);
        UserRole user = new UserRole();
        user.setRole(UserRoleEnum.USER);
        userRoleRepository.save(user);
    }

    private void initUsers() {
        User gamer = new User();
        gamer.setUsername("gamer");
        gamer.setPassword(passwordEncoder.encode("gamer"));
        gamer.setFirstName("gamer");
        gamer.setLastName("gamerov");
        gamer.setEmail("gamer@abv.bg");

        gamer.setRoles(new ArrayList<>());
        gamer.getRoles().add(userRoleRepository.findUserRoleByRole(UserRoleEnum.USER));

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setFirstName("admin");
        admin.setLastName("adminov");
        admin.setEmail("admin@abv.bg");

        admin.setRoles(new ArrayList<>());
        admin.getRoles().add(userRoleRepository.findUserRoleByRole(UserRoleEnum.ADMIN));

        userRepository.save(gamer);
        userRepository.save(admin);


    }


}
