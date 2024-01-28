package com.example.hangman.util;

import com.example.hangman.model.entity.User;
import com.example.hangman.model.entity.UserRole;
import com.example.hangman.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findUserByUsername(username).map(this::mapUser)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));

    }

    private UserDetails mapUser(User user) {
        return org.springframework.security.core.userdetails.User.builder().
                username(user.getUsername()).
                password(user.getPassword()).
                authorities(user.
                        getRoles().
                        stream().map(this::map).
                        toList()).
                build();
    }


    private GrantedAuthority map(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.getRole().name());
    }
}
