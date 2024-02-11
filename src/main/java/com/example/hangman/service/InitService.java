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
    private final WordRepository wordRepository;
    private final CategoryRepository categoryRepository;
    private final DifficultyRepository difficultyRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitService(UserRoleRepository userRoleRepository, WordRepository wordRepository, CategoryRepository categoryRepository, DifficultyRepository difficultyRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.wordRepository = wordRepository;
        this.categoryRepository = categoryRepository;
        this.difficultyRepository = difficultyRepository;
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
        if (difficultyRepository.count() == 0) {
            initDifficulties();
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

    private void initCategories() {
        //HISTORY, GEOGRAPHY, ANIMALS;
        Category history = new Category();
        history.setCategoryEnum(CategoryEnum.HISTORY);
        Category geography = new Category();
        geography.setCategoryEnum(CategoryEnum.GEOGRAPHY);
        Category animals = new Category();
        animals.setCategoryEnum(CategoryEnum.ANIMALS);
        categoryRepository.save(history);
        categoryRepository.save(geography);
        categoryRepository.save(animals);
    }

    private void initDifficulties() {
        Difficulty easy = new Difficulty();
        easy.setDifficultyEnum(DifficultyEnum.EASY);
        Difficulty medium = new Difficulty();
        medium.setDifficultyEnum(DifficultyEnum.MEDIUM);
        Difficulty hard = new Difficulty();
        hard.setDifficultyEnum(DifficultyEnum.HARD);
        difficultyRepository.save(easy);
        difficultyRepository.save(medium);
        difficultyRepository.save(hard);
    }

    private void initWords() {
        Word tiger = new Word();
        tiger.setWord("tiger");
        tiger.setCategory(categoryRepository.findCategoryByCategoryEnum(CategoryEnum.ANIMALS));
        tiger.setDescription("An animal which is from the cats family and can run very fast.");
        tiger.setDifficulty(difficultyRepository.findDifficultyByDifficultyEnum(DifficultyEnum.EASY));

        Word newYork = new Word();
        newYork.setWord("new york");
        newYork.setCategory(categoryRepository.findCategoryByCategoryEnum(CategoryEnum.GEOGRAPHY));
        newYork.setDescription("A very big city which is located somewhere in USA.");
        newYork.setDifficulty(difficultyRepository.findDifficultyByDifficultyEnum(DifficultyEnum.HARD));


        wordRepository.save(tiger);
        wordRepository.save(newYork);
    }


}
