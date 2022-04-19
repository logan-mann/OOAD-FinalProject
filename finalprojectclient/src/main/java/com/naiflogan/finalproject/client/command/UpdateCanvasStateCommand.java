package com.naiflogan.finalproject.client.command;

import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.user.User;

public class UpdateCanvasStateCommand implements Command {

    private ClientModel clientModel;
    private BackendRequestSender backendRequestSender;

    public UpdateCanvasStateCommand(ClientModel clientModel) {
        this.clientModel = clientModel;
        this.backendRequestSender =BackendRequestSender.getInstance();
    }

    @Override
    public void execute() {
        User user = clientModel.getUser();
        Map<String, Canvas> newCanvasMap = backendRequestSender.getCanvases(user.getJsonWebToken());
        clientModel.updateCanvasState(newCanvasMap);
        clientModel.notifyViews();
    }

    
    
}
