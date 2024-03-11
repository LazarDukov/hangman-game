package com.example.hangman.util.validation;

import com.example.hangman.model.entity.User;
import com.example.hangman.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailExistValidator implements ConstraintValidator<EmailExistValidatorInterface, String> {

    private final UserRepository userRepository;

    @Autowired
    public EmailExistValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(EmailExistValidatorInterface constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        User user = this.userRepository.findFirstByEmail(email);
        return user == null;
    }
}
