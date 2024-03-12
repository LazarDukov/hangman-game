package com.example.hangman.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column
    private String username;
    @Column
    private String password;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private Integer points;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Word> guessedWords;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private List<UserRole> roles;

    public User() {
        this.points = 0;
        this.guessedWords = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getPoints() {
        return points;
    }

    public User setPoints(Integer points) {
        this.points = points;
        return this;
    }
    public Set<Word> getGuessedWords() {
        return guessedWords;
    }

    public User setGuessedWords(Set<Word> guessedWords) {
        this.guessedWords = guessedWords;
        return this;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public User setRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }
}
