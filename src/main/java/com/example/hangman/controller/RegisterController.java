package com.example.hangman.controller;

import com.example.hangman.model.dto.UserRegistrationDTO;
import com.example.hangman.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public RegisterController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("/register")
    private String getRegister() {
        return "register";
    }

    @ModelAttribute("register")
    public UserRegistrationDTO initUserRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @PostMapping("/register")
    private String postRegister(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("register", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.register", bindingResult);
            return "redirect:/register";
        }
        userRegistrationService.registerNewUser(userRegistrationDTO);
        return "redirect:/login";
    }
}
