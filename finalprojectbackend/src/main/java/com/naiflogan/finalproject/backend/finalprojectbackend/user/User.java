package com.naiflogan.finalproject.backend.finalprojectbackend.user;

/**
 * Class containing pertinent information about logged in user
 * Contains username and JWT
 */
public class User {
    private String username;
    private String jsonWebToken;

    public User( String username, String token ) {
        this.username = username;
        this.jsonWebToken = token;
    }

    public String getUsername() {
        return username;
    }

    public String getJsonWebToken() {
        return jsonWebToken;
    }

}
