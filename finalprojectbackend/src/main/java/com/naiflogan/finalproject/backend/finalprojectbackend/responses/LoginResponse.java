package com.naiflogan.finalproject.backend.finalprojectbackend.responses;

import com.naiflogan.finalproject.backend.finalprojectbackend.user.User;

public class LoginResponse {

    private User user;
    private String error;


    public LoginResponse(User user, String error) {
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
