package com.naiflogan.finalproject.client.command;

import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.request.AddCanvasRequest;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.user.User;

/**
 * CreateCanvasCommand sends a request to create a new canvas to the backend
 * Part of COMMAND PATTERN
 */
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
        //Get the current logged in user from clientModel
        User user = clientModel.getUser();
        //Build request object with canvas parameters and user's JWT
        AddCanvasRequest addCanvasRequest = new AddCanvasRequest(canvasName, user.getJsonWebToken(), isPublic);
        //Send request to backend via request sender
        BackendRequestSender.getInstance().addCanvas(addCanvasRequest);
    }

    
    
}
