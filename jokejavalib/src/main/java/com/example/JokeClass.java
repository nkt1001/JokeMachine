package com.example;

public class JokeClass {
    private JokeBase jokeBase = new JokeBase();
    public Joke tellJoke() {
        return jokeBase.getNextJoke();
    }
}
