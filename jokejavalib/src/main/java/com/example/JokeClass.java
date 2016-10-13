package com.example;

public class JokeClass {
    public String getFreshJoke() {
        retrieveJoke();
        return "Hahaha! Omg! Good joke!";
    }

    private void retrieveJoke() {
        //TODO get joke from server
    }
}
