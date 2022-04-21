package com.naiflogan.finalproject.client.command;

import com.naiflogan.finalproject.client.factory.ShapeCreationStrategyFactory;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.shapes.ShapeType;
import com.naiflogan.finalproject.client.strategy.ShapeCreationStrategy;

public class ChangeShapeTypeCommand implements Command {

    private ShapeType shapeType;
    private ClientModel clientModel;

    public ChangeShapeTypeCommand(ShapeType shapeType, ClientModel clientModel) {
        this.shapeType = shapeType;
        this.clientModel = clientModel;
    }

    @Override
    public void execute() {
        System.out.println(shapeType);
        ShapeCreationStrategy newStrategy = ShapeCreationStrategyFactory.getShapeCreationStrategy(shapeType);
        clientModel.setCurrentShapeType(shapeType);
        clientModel.setShapeCreationStrategy(newStrategy);
        clientModel.notifyViews();
    }
    
}
