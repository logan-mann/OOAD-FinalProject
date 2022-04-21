package com.naiflogan.finalproject.client.command;

import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.request.AddCanvasRequest;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.user.User;

public class CreateCanvasCommand implements Command {

    private ClientModel clientModel;
    private String canvasName;
    private boolean isPublic;

    public CreateCanvasCommand(String canvasName, boolean isPublic, ClientModel clientModel) {
        this.canvasName = canvasName;
        this.isPublic = isPublic;
        this.clientModel = clientModel;
    }

    @Override
    public void execute() {
        User user = clientModel.getUser();
        AddCanvasRequest addCanvasRequest = new AddCanvasRequest(canvasName, user.getJsonWebToken(), isPublic);
        BackendRequestSender.getInstance().addCanvas(addCanvasRequest);
    }

    
    
}
