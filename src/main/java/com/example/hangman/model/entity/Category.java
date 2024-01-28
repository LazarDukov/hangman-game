package com.example.hangman.model.entity;

import com.example.hangman.model.enums.CategoryEnum;
import com.example.hangman.model.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    public Category() {
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public Category setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
