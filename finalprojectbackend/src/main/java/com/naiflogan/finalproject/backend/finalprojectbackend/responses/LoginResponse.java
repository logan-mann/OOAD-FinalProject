package com.naiflogan.finalproject.backend.finalprojectbackend.responses;

public class LoginResponse {

    private String jsonWebToken;
    private String error;


    public LoginResponse(String jsonWebToken, String error) {
        this.jsonWebToken = jsonWebToken;
        this.error = error;
    }
    

    public String getJsonWebToken() {
        return this.jsonWebToken;
    }

    public String getError() {
        return error;
    }
    
}
