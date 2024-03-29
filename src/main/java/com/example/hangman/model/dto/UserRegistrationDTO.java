package com.example.hangman.model.dto;


import com.example.hangman.util.validation.EmailExistValidatorInterface;
import com.example.hangman.util.validation.PasswordMatchValidatorInterface;
import com.example.hangman.util.validation.UsernameExistValidatorInterface;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@PasswordMatchValidatorInterface(password = "password", confirmPassword = "confirmPassword")
public class UserRegistrationDTO {
    @NotNull(message = "This field cannot be empty!")
    @Size(min = 2, max = 20, message = "Size must be between 2 and 20 symbols!")
    @UsernameExistValidatorInterface(message = "Username already registered!")
    private String username;
    @NotNull(message = "This field cannot be empty!")
    @Size(min = 6, max = 20, message = "Password should be between 6 and 20 symbols!")
    private String password;
    private String confirmPassword;
    @NotNull(message = "This field cannot be empty!")
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 symbols!")
    private String firstName;
    @NotNull(message = "This field cannot be empty!")
    @Size(min=2, max = 20,  message = "Last name should be between 2 and 20 symbols!")
    private String lastName;
    @Email
    @NotEmpty(message = "This field cannot be empty!")
    @EmailExistValidatorInterface(message = "Username with this email already registered!")
    private String email;
    public UserRegistrationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }


}
