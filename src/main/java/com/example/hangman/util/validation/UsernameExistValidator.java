package com.example.hangman.util.validation;

import com.example.hangman.model.entity.User;
import com.example.hangman.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameExistValidator implements ConstraintValidator<UsernameExistValidatorInterface, String> {
    private final UserRepository userRepository;


    @Autowired
    public UsernameExistValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UsernameExistValidatorInterface constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        User user = this.userRepository.findFirstByUsername(username);
        return user == null;
    }
}
