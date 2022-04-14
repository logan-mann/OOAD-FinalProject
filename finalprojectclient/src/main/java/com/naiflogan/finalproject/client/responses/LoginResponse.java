package com.naiflogan.finalproject.client.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    private String jsonWebToken;
    private String error;


    public LoginResponse(
        @JsonProperty("jsonWebToken") String jsonWebToken, 
        @JsonProperty("error") String error) {
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
