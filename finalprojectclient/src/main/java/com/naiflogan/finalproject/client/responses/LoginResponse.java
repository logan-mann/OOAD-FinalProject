package com.naiflogan.finalproject.client.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naiflogan.finalproject.client.user.User;

public class LoginResponse {

    private User user;
    private String error;


    public LoginResponse(@JsonProperty("user") User user, @JsonProperty("error") String error) {
        this.user = user;
        this.error = error;
    }
    

    public User getUser() {
        return this.user;
    }

    public String getError() {
        return error;
    }
    
}
