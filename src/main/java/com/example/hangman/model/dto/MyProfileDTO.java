package com.example.hangman.model.dto;

public class MyProfileDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer points;

    public MyProfileDTO() {
    }

    public String getUsername() {
        return username;
    }

    public MyProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public MyProfileDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public MyProfileDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MyProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getPoints() {
        return points;
    }

    public MyProfileDTO setPoints(Integer points) {
        this.points = points;
        return this;
    }
}
