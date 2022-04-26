package com.naiflogan.finalproject.client.controller;

import com.naiflogan.finalproject.client.command.CommandInvoker;
import com.naiflogan.finalproject.client.command.CreateAccountCommand;
import com.naiflogan.finalproject.client.command.LoginCommand;
import com.naiflogan.finalproject.client.model.ClientModel;

/**
 * Controller to handle user authentication functions
 * Decouples logic of these functions from views that wish to perform authentication actions on user input
 * Part of MVC PATTERN
 */
public class AuthController {

    //Client Model that the controller references/modifies
    private ClientModel clientModel;
    //Command invoker to issue/execute commands
    private CommandInvoker commandInvoker;

    public AuthController(ClientModel clientModel) {
        this.clientModel = clientModel;
        this.commandInvoker = CommandInvoker.getInstance();
    }

    //Logs in a user with passed credentials
    public void login(String username, String password) {
        //Create login command with clientModel/user credentials
        LoginCommand loginCommand = new LoginCommand(username, password, clientModel);
        //Set and execute command
        commandInvoker.setCommand(loginCommand);
        commandInvoker.executeCommand();
    }

    //To logout, simply clear clientModel's user information and update views
    public void logout() {
        clientModel.setUser(null);
        clientModel.setLoggedIn(false);
        clientModel.notifyViews();    
    }

    //Create a new account with passed credentials
    public void createAccount(String username, String password) {
        //Create and execute CreateAccountCommand with credentials
        CreateAccountCommand command = new CreateAccountCommand(username, password);
        commandInvoker.setCommand(command);
        commandInvoker.executeCommand();
    }


    
}
