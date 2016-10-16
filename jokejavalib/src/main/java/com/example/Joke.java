package com.example;

/**
 * Created by nkt01 on 16.10.2016.
 */
public class Joke {
    private String mJoke;

    public Joke(String mJoke) {
        this.mJoke = mJoke;
    }

    public String getJoke() {
        return mJoke;
    }

    public void setJoke(String mJoke) {
        this.mJoke = mJoke;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Joke joke = (Joke) o;

        return mJoke.equals(joke.mJoke);

    }

    @Override
    public int hashCode() {
        return mJoke.hashCode();
    }
}
