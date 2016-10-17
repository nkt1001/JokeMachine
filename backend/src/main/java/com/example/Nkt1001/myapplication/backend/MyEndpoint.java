package com.example.Nkt1001.myapplication.backend;

import com.example.Joke;
import com.example.JokeClass;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Nkt1001.example.com",
                ownerName = "backend.myapplication.Nkt1001.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name="obtainJoke", httpMethod = "GET")
    public Joke obtainJoke() {
        JokeClass jokeClass = new JokeClass();
        return jokeClass.tellJoke();
    }
}
