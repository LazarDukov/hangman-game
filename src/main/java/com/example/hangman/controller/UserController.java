package com.example.hangman.controller;

import com.example.hangman.model.entity.User;
import com.example.hangman.service.UserService;
import com.example.hangman.util.WordSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final WordSession wordSession;


    public UserController(UserService userService, WordSession wordSession) {
        this.userService = userService;
        this.wordSession = wordSession;
    }

    @GetMapping("/won")
    private String getWonPage(Model model, Principal principal) {
        String secretWord = wordSession.getWord();
        userService.updatePoints(principal, wordSession);
        userService.addGuessedWord(principal, secretWord);
        model.addAttribute("secretWord", secretWord);
        if (userService.checkIsWinner(principal)) {
            String winnerMsg = "YOU ARE THE WINNER!";
            model.addAttribute("youAreWinner", winnerMsg);
            userService.restart();
            return "won";
        }
        return "won";
    }

    @GetMapping("/ranking")
    private String getRankingPage(Model model) {
        List<User> ranking = userService.getRanking();
        model.addAttribute("ranking", ranking);
        return "ranking";
    }

    @GetMapping("/my-profile")
    private String getMyProfilePage(Principal principal, Model model) {
        User user = userService.getCurrentUser(principal);
        model.addAttribute("currentUser", user);
        return "/my-profile";
    }

    @GetMapping("/manage-admins")
    private String manageAdminsPage(Model model) {
        List<User> allAdmins = userService.getAllAdmins();
        model.addAttribute("allAdmins", allAdmins);
        return "manage-admins";
    }

    @GetMapping("/manage-admins/{id}")
    private String removeAdmin(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.removeAdmin(user);
        return "redirect:/manage-admins";
    }

    @GetMapping("/manage-users")
    private String manageUsersPage(Model model) {
        List<User> allUsers = userService.getAllUsersWithoutAdmins();
        model.addAttribute("allUsers", allUsers);
        return "manage-users";
    }

    @GetMapping("/manage-users/{id}")
    private String makeUserAnAdmin(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.makeUserAnAdmin(user);
        return "redirect:/manage-users";
    }

}
