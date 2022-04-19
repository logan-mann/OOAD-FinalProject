package com.naiflogan.finalproject.client.command;

import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.request.AddShapeRequest;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.shapes.Shape;
import com.naiflogan.finalproject.client.user.User;

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
        User user = clientModel.getUser();
        AddShapeRequest shapeRequest = new AddShapeRequest(shape, canvasName, user.getJsonWebToken());
        BackendRequestSender.getInstance().addShape(shapeRequest);
        Map<String, Canvas> newCanvases = BackendRequestSender.getInstance().getCanvases(user.getJsonWebToken());
        clientModel.updateCanvasState(newCanvases);
        clientModel.notifyViews();
    }
    
}
