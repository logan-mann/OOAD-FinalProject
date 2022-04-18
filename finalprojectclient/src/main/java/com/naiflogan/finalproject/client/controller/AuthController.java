package com.naiflogan.finalproject.client.controller;

import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.request.CreateAccountRequest;
import com.naiflogan.finalproject.client.request.LoginRequest;
import com.naiflogan.finalproject.client.requestsender.AuthRequestSender;


public class AuthController {

    private ClientModel clientModel;
    private AuthRequestSender authRequestSender;

    public AuthController(ClientModel clientModel) {
        this.clientModel = clientModel;
        this.authRequestSender = AuthRequestSender.getInstance();
    }

    public void login(String username, String password) {
        System.out.println("running this");
        LoginRequest loginRequest = new LoginRequest(username, password);
        String jwt = authRequestSender.login(loginRequest);
        if (!jwt.equals("")) {
            clientModel.setJwt(jwt);
            clientModel.setLoggedIn(true);
            clientModel.notifyViews();
        } else {
            clientModel.setJwt("");
            clientModel.setLoggedIn(false);
            clientModel.notifyViews();
        }
    }

    public void logout() {
        clientModel.setJwt("");
        clientModel.setLoggedIn(false);
        clientModel.notifyViews();    
    }

    public void createAccount(String username, String password) {

        CreateAccountRequest createAccountRequest = new CreateAccountRequest(username, password);
        authRequestSender.createAccount(createAccountRequest);
    }


    
}
