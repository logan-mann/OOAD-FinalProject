package com.naiflogan.finalproject.client.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class containing pertinent information about logged in user
 * Contains username and JWT
 */
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
