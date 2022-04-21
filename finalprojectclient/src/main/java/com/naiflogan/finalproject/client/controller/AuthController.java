package com.naiflogan.finalproject.client.controller;

import com.naiflogan.finalproject.client.command.CommandInvoker;
import com.naiflogan.finalproject.client.command.CreateAccountCommand;
import com.naiflogan.finalproject.client.command.LoginCommand;
import com.naiflogan.finalproject.client.model.ClientModel;


public class AuthController {

    private ClientModel clientModel;
    private CommandInvoker commandInvoker;

    public AuthController(ClientModel clientModel) {
        this.clientModel = clientModel;
        this.commandInvoker = CommandInvoker.getInstance();
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
        CreateAccountCommand command = new CreateAccountCommand(username, password);
        commandInvoker.setCommand(command);
        commandInvoker.executeCommand();
    }


    
}
