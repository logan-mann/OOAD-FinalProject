package com.naiflogan.finalproject.client.command;

import java.util.HashMap;
import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.request.LoginRequest;
import com.naiflogan.finalproject.client.requestsender.AuthRequestSender;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.user.User;

/**
 * LoginCommand handles sending login requests to backend and subsequent updating of clientModel
 * Part of COMMAND PATTERN
 */
public class LoginCommand implements Command {

    private ClientModel clientModel;
    private String username;
    private String password;

    public LoginCommand(String username, String password, ClientModel clientModel) {
        this.username = username;
        this.password = password;
        this.clientModel = clientModel;
    }


    @Override
    public void execute() {
        //Get requestSender instances
        AuthRequestSender authRequestSender = AuthRequestSender.getInstance();
        BackendRequestSender backendRequestSender= BackendRequestSender.getInstance();

        //Build request object with command's attributes
        LoginRequest loginRequest = new LoginRequest(username, password);
        //Send request to backend, returns User object on success, null on failure
        User user = authRequestSender.login(loginRequest);
        //If login was successful, need to update clientModel accordingly
        if (user != null) {
            //Request user's canvases from backend with JWT issued from backend
            Map<String, Canvas> canvasesMap = backendRequestSender.getCanvases(user.getJsonWebToken());
            //Set user and canvases objects in clientModel
            clientModel.setUser(user);
            clientModel.setCanvases(canvasesMap);
            //If canvasMap isn't empty, select one to display as selected canvas
            if (!canvasesMap.entrySet().isEmpty()) {
                Canvas canvas = canvasesMap.values().iterator().next();
                clientModel.setCurrentCanvas(canvas.getName());
            }
            //Set loggedIn to true
            clientModel.setLoggedIn(true);
            
        } else {
            //If login failed, clear clientModel's user,loggedIn, and canvases attributes
            clientModel.setUser(null);
            clientModel.setLoggedIn(false);
            clientModel.setCanvases(new HashMap<>());
        }
        //Finally, notify clientModel's subscribed views
        clientModel.notifyViews();
    }
    
}
