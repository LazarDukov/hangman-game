package com.example.hangman;

import com.example.hangman.model.entity.User;
import com.example.hangman.model.entity.UserRole;
import com.example.hangman.model.enums.UserRoleEnum;
import com.example.hangman.repository.UserRepository;
import com.example.hangman.util.ApplicationUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HangmanUserDetailsTests {

    @Mock
    private UserRepository mockUserRepository;

    private ApplicationUserDetailsService serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new ApplicationUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_userExist() {
        User testUser = new User().setUsername("TestTesterov")
                .setPassword("topsecret").setFirstName("Test").setLastName("Testerov")
                .setEmail("TestTesterov@email.com").setPoints(0)
                .setGuessedWords(new HashSet<>()).setRoles(List.of(new UserRole().setRole(UserRoleEnum.USER)));
        when(mockUserRepository.findUserByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));


        UserDetails userDetails = serviceToTest.loadUserByUsername(testUser.getUsername());
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUser.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUser.getRoles().size(), 1);
    }

    @Test
    void testLoadUserByUsername_notUserExist() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("ThisUsernameIsNotExist"));
    }

}
