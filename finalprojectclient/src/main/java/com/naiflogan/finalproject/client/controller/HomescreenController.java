package com.naiflogan.finalproject.client.controller;

import com.naiflogan.finalproject.client.command.ChangeShapeTypeCommand;
import com.naiflogan.finalproject.client.command.CommandInvoker;
import com.naiflogan.finalproject.client.command.CreateCanvasCommand;
import com.naiflogan.finalproject.client.command.PlaceShapeCommand;
import com.naiflogan.finalproject.client.command.UpdateCanvasStateCommand;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.shapes.Shape;
import com.naiflogan.finalproject.client.shapes.ShapeType;

/**
 * Controller to perform actions taken from the main views of the application
 * Decouples logic of key application functions from the Views that enact them based on user input
 * Keeps modification of Model objects separate from Views that use them
 * Part of MVC PATTERN
 */
public class HomescreenController {

    private ClientModel clientModel;
    private CommandInvoker commandInvoker;

    public HomescreenController(ClientModel clientModel) {
        this.clientModel = clientModel;
        this.commandInvoker = CommandInvoker.getInstance();
    }

    //Place a new shape, takese in shape object to be placed
    public void placeShape(Shape shape) {
        //Get the current canvas that the user is operating on from clientModel
        String canvasName = clientModel.getCurrentCanvas().getName();
        //Create PlaceShapeCommand and execute it via commandInvoker
        PlaceShapeCommand shapeCommand = new PlaceShapeCommand(shape, canvasName, clientModel);
        commandInvoker.setCommand(shapeCommand);
        commandInvoker.executeCommand();
    }

    //Get/return hex string representing currentPenColor from clientModel
    public String getClientModelPenColor() {
        return clientModel.getCurrentPenColor();
    }

    //Switch currentCanvas in clientModel, notify views
    public void switchCanvas(String canvasName) {
        clientModel.setCurrentCanvas(canvasName);
        clientModel.notifyViews();
    }

    //Update canvas state from backend service
    public void updateCanvasState() {
        //If user is logged in, create an UpdateStateCommand and execute it
        if (clientModel.isLoggedIn()) {
            UpdateCanvasStateCommand updateCommand = new UpdateCanvasStateCommand(clientModel);
            commandInvoker.setCommand(updateCommand);
            commandInvoker.executeCommand();
        }
    }

    //Set currentPenColor in clientModel to new value
    //Takes in hex string representing RGB color
    public void setCurrentPenColor(String penColor) {
        //Set clientModel's pen color to new color, notify views
        this.clientModel.setCurrentPenColor(penColor);
        this.clientModel.notifyViews();
    }

    //Change current shape type being placed on canvas to new shapeType
    public void setCurrentShapeType(ShapeType shapeType) {
        //Create and execute ChangeShapeTypeCommand with clientModel and shapeType passed
        ChangeShapeTypeCommand command = new ChangeShapeTypeCommand(shapeType, clientModel);
        commandInvoker.setCommand(command);
        commandInvoker.executeCommand();
    }

    //Function to navigate to create canvas view
    public void goToCreateCanvasView() {
        //Set state in clientModel, notify views
        this.clientModel.setIsOnCreateCanvasScreen(true);
        clientModel.notifyViews();
    }
    
    //Function to navigate to main application view
    public void goToHomescreenView() {
        //Set state in clientModel, notify views
        this.clientModel.setIsOnCreateCanvasScreen(false);
        clientModel.notifyViews();
    }

    //Function to create new canvas with parameters passed
    public void createNewCanvas(String canvasName, boolean isPublic) {
        //Instantiate new CreateCanvasCommmand, execute it
        CreateCanvasCommand createCommand = new CreateCanvasCommand(canvasName, isPublic, clientModel);
        commandInvoker.setCommand(createCommand);
        commandInvoker.executeCommand();
        //Update canvas state once finished, return to main application view
        updateCanvasState();
        goToHomescreenView();
    }

    //To logout, simply clear clientModel's user information and update views
    public void logout() {
        this.clientModel.setUser(null);
        this.clientModel.setLoggedIn(false);
        this.clientModel.notifyViews();
    }
}
