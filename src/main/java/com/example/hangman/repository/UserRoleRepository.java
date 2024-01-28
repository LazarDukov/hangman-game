package com.example.hangman.repository;

import com.example.hangman.model.entity.UserRole;
import com.example.hangman.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findUserRoleByRole(UserRoleEnum role);
}
