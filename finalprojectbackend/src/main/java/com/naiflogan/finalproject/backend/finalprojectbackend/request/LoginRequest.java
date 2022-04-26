package com.naiflogan.finalproject.backend.finalprojectbackend.request;

/**
 * Request object containing fields needed for request to /login in backend service
 */
public class LoginRequest {
    private String username;
    private String password;


    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return this.username;
    }


    public String getPassword() {
        return this.password;
    }
}
