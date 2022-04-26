package com.naiflogan.finalproject.backend.finalprojectbackend.request;

/**
 * Request object containing fields needed for request to /create_account in backend service
 */
public class CreateAccountRequest {

    private String username;
    private String password;


    public CreateAccountRequest(String username, String password) {
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
