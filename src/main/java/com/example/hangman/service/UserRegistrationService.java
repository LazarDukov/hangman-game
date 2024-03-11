package com.example.hangman.service;

import com.example.hangman.model.dto.UserRegistrationDTO;
import com.example.hangman.model.entity.User;
import com.example.hangman.model.enums.UserRoleEnum;
import com.example.hangman.repository.UserRepository;
import com.example.hangman.repository.UserRoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    public void registerNewUser(@Valid UserRegistrationDTO userRegistrationDTO) {
        User newUser = new User();
        newUser.setUsername(userRegistrationDTO.getUsername())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()))
                .setFirstName(userRegistrationDTO.getFirstName())
                .setLastName(userRegistrationDTO.getLastName())
                .setEmail(userRegistrationDTO.getEmail())
                .setRoles(new ArrayList<>());
        newUser.getRoles().add(userRoleRepository.findUserRoleByRole(UserRoleEnum.USER));
        userRepository.save(newUser);
    }

    @Transactional
    public void addAdmin(@Valid UserRegistrationDTO userRegistrationDTO) {
        User newAdmin = new User();
        newAdmin.setUsername(userRegistrationDTO.getUsername())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()))
                .setFirstName(userRegistrationDTO.getFirstName())
                .setLastName(userRegistrationDTO.getLastName())
                .setEmail(userRegistrationDTO.getEmail())
                .setRoles(new ArrayList<>());
        newAdmin.getRoles().add(userRoleRepository.findUserRoleByRole(UserRoleEnum.ADMIN));
        userRepository.save(newAdmin);

    }
}
