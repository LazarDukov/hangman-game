package com.example.hangman.repository;

import com.example.hangman.model.entity.Category;
import com.example.hangman.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
