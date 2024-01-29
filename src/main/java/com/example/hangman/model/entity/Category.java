package com.example.hangman.model.entity;

import com.example.hangman.model.enums.CategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column
    private CategoryEnum categoryEnum;

    public Category() {
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public Category setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
        return this;
    }
}
