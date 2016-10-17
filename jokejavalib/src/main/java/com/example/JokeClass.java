package com.example;

public class JokeClass {

    JokeBuilder jokeBuilder = new JokeBuilder();

    public Joke tellJoke() {
        return jokeBuilder.getNextJoke();
    }
}
