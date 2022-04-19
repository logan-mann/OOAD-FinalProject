package com.naiflogan.finalproject.client.command;

import java.util.HashMap;
import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.request.LoginRequest;
import com.naiflogan.finalproject.client.requestsender.AuthRequestSender;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.user.User;

public class LoginCommand implements Command {

    private ClientModel clientModel;
    private String username;
    private String password;
    private AuthRequestSender authRequestSender = AuthRequestSender.getInstance();
    private BackendRequestSender backendRequestSender = BackendRequestSender.getInstance();

    public LoginCommand(String username, String password, ClientModel clientModel) {
        this.username = username;
        this.password = password;
        this.clientModel = clientModel;
    }


    @Override
    public void execute() {

        System.out.println("running this");
        LoginRequest loginRequest = new LoginRequest(username, password);
        User user = authRequestSender.login(loginRequest);
        if (user != null) {
            Map<String, Canvas> canvasesMap = backendRequestSender.getCanvases(user.getJsonWebToken());
            clientModel.setUser(user);
            clientModel.setCanvases(canvasesMap);
            if (!canvasesMap.entrySet().isEmpty()) {
                Canvas canvas = canvasesMap.values().iterator().next();
                clientModel.setCurrentCanvas(canvas.getName());
            }
            clientModel.setLoggedIn(true);
            
        } else {
            clientModel.setUser(null);
            clientModel.setLoggedIn(false);
            clientModel.setCanvases(new HashMap<>());
        }
        clientModel.notifyViews();
    }
    
}
