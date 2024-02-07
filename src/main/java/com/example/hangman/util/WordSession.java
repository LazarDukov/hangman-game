package com.example.hangman.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class WordSession {
    private String word;

    public WordSession() {
    }

    public String getWord() {
        return word;
    }

    public WordSession setWord(String word) {
        this.word = word;
        return this;
    }
}
