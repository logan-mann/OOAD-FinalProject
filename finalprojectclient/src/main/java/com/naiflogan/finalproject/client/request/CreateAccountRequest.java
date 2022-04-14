package com.naiflogan.finalproject.client.request;

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
