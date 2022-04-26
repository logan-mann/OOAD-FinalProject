package com.naiflogan.finalproject.client.command;

import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.user.User;

/**
 * Command to get updated canvas state from backend service
 * Part of COMMAND PATTERN
 */
public class UpdateCanvasStateCommand implements Command {

    private ClientModel clientModel;

    public UpdateCanvasStateCommand(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    @Override
    public void execute() {
        //Get requestSender instance
        BackendRequestSender backendRequestSender = BackendRequestSender.getInstance();
        //Get logged in user from clientModel
        User user = clientModel.getUser();
        //Get most up-to-date canvas information for user via requestSender, pass in JWT
        Map<String, Canvas> newCanvasMap = backendRequestSender.getCanvases(user.getJsonWebToken());
        //Update clientModel with new state, notify views
        clientModel.updateCanvasState(newCanvasMap);
        clientModel.notifyViews();
    }

    
    
}
