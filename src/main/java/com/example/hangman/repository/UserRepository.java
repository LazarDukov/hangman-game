package com.example.hangman.repository;

import com.example.hangman.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    List<User> findAllByOrderByPointsDesc();

    User findFirstByUsername(String username);

    User findFirstByEmail(String email);
}
