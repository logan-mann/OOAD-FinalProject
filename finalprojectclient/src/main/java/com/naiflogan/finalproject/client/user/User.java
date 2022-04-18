package com.naiflogan.finalproject.client.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String username;
    private String jsonWebToken;


    public User(@JsonProperty("username") String username, @JsonProperty("jsonWebToken") String jsonWebToken) {
        this.username = username;
        this.jsonWebToken = jsonWebToken;
    }


    public String getUsername() {
        return this.username;
    }

    public String getJsonWebToken() {
        return this.jsonWebToken;
    }
    

}
