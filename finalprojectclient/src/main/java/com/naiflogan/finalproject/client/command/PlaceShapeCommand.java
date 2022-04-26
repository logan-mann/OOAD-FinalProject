package com.naiflogan.finalproject.client.command;

import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.request.AddShapeRequest;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.shapes.Shape;
import com.naiflogan.finalproject.client.user.User;

/**
 * PlaceShapeCommand handles sending requests to place a new shape to the backend service
 * Part of COMMAND PATTERN
 */
public class PlaceShapeCommand implements Command {

    private Shape shape;
    private String canvasName;
    private ClientModel clientModel;

    public PlaceShapeCommand(Shape shape, String canvasName, ClientModel clientModel) {
        this.shape = shape;
        this.canvasName = canvasName;
        this.clientModel = clientModel;
    }

    @Override
    public void execute() {
        //Get logged in user from clientModel
        User user = clientModel.getUser();
        //Build addShapeRequest from command attributes, user's JWT
        AddShapeRequest shapeRequest = new AddShapeRequest(shape, canvasName, user.getJsonWebToken());
        //Send addShapeRequest to backend service via requestSender instance
        BackendRequestSender.getInstance().addShape(shapeRequest);
        //Once shape is placed, get new canvas status and update clientModel + notify views
        Map<String, Canvas> newCanvases = BackendRequestSender.getInstance().getCanvases(user.getJsonWebToken());
        clientModel.updateCanvasState(newCanvases);
        clientModel.notifyViews();
    }
    
}
