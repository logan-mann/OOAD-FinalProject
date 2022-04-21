package com.naiflogan.finalproject.client.command;

import com.naiflogan.finalproject.client.request.CreateAccountRequest;
import com.naiflogan.finalproject.client.requestsender.AuthRequestSender;

public class CreateAccountCommand implements Command {
    private String username;
    private String password;

    public CreateAccountCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute() {
        CreateAccountRequest createRequest = new CreateAccountRequest(username, password);
        AuthRequestSender.getInstance().createAccount(createRequest);
    }
    
}
