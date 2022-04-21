package com.naiflogan.finalproject.client.command;

import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.user.User;

public class UpdateCanvasStateCommand implements Command {

    private ClientModel clientModel;

    public UpdateCanvasStateCommand(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    @Override
    public void execute() {
        BackendRequestSender backendRequestSender = BackendRequestSender.getInstance();
        User user = clientModel.getUser();
        Map<String, Canvas> newCanvasMap = backendRequestSender.getCanvases(user.getJsonWebToken());
        clientModel.updateCanvasState(newCanvasMap);
        clientModel.notifyViews();
    }

    
    
}
