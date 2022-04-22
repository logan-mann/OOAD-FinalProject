package com.naiflogan.finalproject.client.controller;

import com.naiflogan.finalproject.client.command.ChangeShapeTypeCommand;
import com.naiflogan.finalproject.client.command.CommandInvoker;
import com.naiflogan.finalproject.client.command.CreateCanvasCommand;
import com.naiflogan.finalproject.client.command.PlaceShapeCommand;
import com.naiflogan.finalproject.client.command.UpdateCanvasStateCommand;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.shapes.Shape;
import com.naiflogan.finalproject.client.shapes.ShapeType;

public class HomescreenController {

    private ClientModel clientModel;
    private CommandInvoker commandInvoker;

    public String getClientModelPenColor() {
        return clientModel.getCurrentPenColor();
    }

    public HomescreenController(ClientModel clientModel) {
        this.clientModel = clientModel;
        this.commandInvoker = CommandInvoker.getInstance();
    }

    public void placeShape(Shape shape) {
        String canvasName = clientModel.getCurrentCanvas().getName();
        System.out.println("PLACE SHAPE COMMAND SHAPE:" + shape);
        PlaceShapeCommand shapeCommand = new PlaceShapeCommand(shape, canvasName, clientModel);
        commandInvoker.setCommand(shapeCommand);
        commandInvoker.executeCommand();
    }


    public void switchCanvas(String canvasName) {
        clientModel.setCurrentCanvas(canvasName);
        clientModel.notifyViews();
    }

    public void updateCanvasState() {
        if (clientModel.isLoggedIn()) {
            UpdateCanvasStateCommand updateCommand = new UpdateCanvasStateCommand(clientModel);
            commandInvoker.setCommand(updateCommand);
            commandInvoker.executeCommand();
        }
    }

    public void setCurrentPenColor(String penColor) {
        this.clientModel.setCurrentPenColor(penColor);
        this.clientModel.notifyViews();
    }

    public void setCurrentShapeType(ShapeType shapeType) {
        ChangeShapeTypeCommand command = new ChangeShapeTypeCommand(shapeType, clientModel);
        commandInvoker.setCommand(command);
        commandInvoker.executeCommand();
    }

    public void goToCreateCanvasView() {
        this.clientModel.setIsOnCreateCanvasScreen(true);
        clientModel.notifyViews();
    }

    public void goToHomescreenView() {
        this.clientModel.setIsOnCreateCanvasScreen(false);
        clientModel.notifyViews();
    }

    public void createNewCanvas(String canvasName, boolean isPublic) {
        CreateCanvasCommand createCommand = new CreateCanvasCommand(canvasName, isPublic, clientModel);
        commandInvoker.setCommand(createCommand);
        commandInvoker.executeCommand();
        updateCanvasState();
        goToHomescreenView();
    }

    public void logout() {
        this.clientModel.setUser(null);
        this.clientModel.setLoggedIn(false);
        this.clientModel.notifyViews();
    }
}
