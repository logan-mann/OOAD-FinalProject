package com.naiflogan.finalproject.client.controller;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.shapes.Shape;

public class HomescreenController {

    private ClientModel clientModel;

    public HomescreenController(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    public void placeShape(Shape shape) {
        
    }


    public void switchCanvas(String canvasName) {
        clientModel.setCurrentCanvas(canvasName);
        clientModel.notifyViews();
    }


    //TODO - Uncomment  and fix once dependencies are implemented
    /*
    private HomescreenModel model;
    private Invoker commandInvoker;

    public HomescreenController(HomescreenModel model, Invoker commandInvoker) {
        this.model = model;
        this.commandInvoker = commandInvoker;
    }

    public void placeShapes(Shape) {
        commandInvoker.execute(new PlaceShapeCommand(model, shape));
    }

    public void switchShapeType(ShapeType shapeType) {
        commandInvoker.execute(new SwitchShapeTypeCommand(model, shapeType));
    }

    public void switchCanvas(Canvas canvas) {
        commandInvoker.execute(new SwitchCanvasCommand(model, canvas));
    }

    public void createCanvas(String name, boolean isPublic) {
        commandInvoker.execute(new CreateCanvasCommand(model, name, isPublic));
    }
    */
}
