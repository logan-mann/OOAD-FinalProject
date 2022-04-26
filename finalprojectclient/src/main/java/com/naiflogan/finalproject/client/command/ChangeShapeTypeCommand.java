package com.naiflogan.finalproject.client.command;

import com.naiflogan.finalproject.client.factory.ShapeCreationStrategyFactory;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.shapes.ShapeType;
import com.naiflogan.finalproject.client.strategy.ShapeCreationStrategy;


/**
 * Command for changing current shape type being placed
 * Part of COMMAND PATTTERN
 */
public class ChangeShapeTypeCommand implements Command {

    private ShapeType shapeType;
    private ClientModel clientModel;

    public ChangeShapeTypeCommand(ShapeType shapeType, ClientModel clientModel) {
        this.shapeType = shapeType;
        this.clientModel = clientModel;
    }

    @Override
    public void execute() {
        //Get the proper shape creation strategy object for shapeType from factory
        ShapeCreationStrategy newStrategy = ShapeCreationStrategyFactory.getShapeCreationStrategy(shapeType);
        //Update clientModel's current shapeType and shapeCreationStrategy, notify views
        clientModel.setCurrentShapeType(shapeType);
        clientModel.setShapeCreationStrategy(newStrategy);
        clientModel.notifyViews();
    }
    
}
