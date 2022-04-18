package com.naiflogan.finalproject.client.controller;

import com.naiflogan.finalproject.client.command.CommandInvoker;
import com.naiflogan.finalproject.client.command.LoginCommand;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.request.CreateAccountRequest;
import com.naiflogan.finalproject.client.request.LoginRequest;
import com.naiflogan.finalproject.client.requestsender.AuthRequestSender;
import com.naiflogan.finalproject.client.user.User;


public class AuthController {

    private ClientModel clientModel;
    private CommandInvoker commandInvoker;
    private AuthRequestSender authRequestSender;

    public AuthController(ClientModel clientModel) {
        this.clientModel = clientModel;
        this.commandInvoker = CommandInvoker.getInstance();
        this.authRequestSender = AuthRequestSender.getInstance();
    }

    public void login(String username, String password) {
        LoginCommand loginCommand = new LoginCommand(username, password, clientModel);
        commandInvoker.setCommand(loginCommand);
        commandInvoker.executeCommand();
    }

    public void logout() {
        clientModel.setUser(null);
        clientModel.setLoggedIn(false);
        clientModel.notifyViews();    
    }

    public void createAccount(String username, String password) {

        CreateAccountRequest createAccountRequest = new CreateAccountRequest(username, password);
        authRequestSender.createAccount(createAccountRequest);
    }


    
}
