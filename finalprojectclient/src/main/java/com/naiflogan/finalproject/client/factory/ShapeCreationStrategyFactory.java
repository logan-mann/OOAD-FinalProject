package com.naiflogan.finalproject.client.factory;

import com.naiflogan.finalproject.client.shapes.ShapeType;
import com.naiflogan.finalproject.client.strategy.CircleCreationStrategy;
import com.naiflogan.finalproject.client.strategy.LineCreationStrategy;
import com.naiflogan.finalproject.client.strategy.RectangleCreationStrategy;
import com.naiflogan.finalproject.client.strategy.ShapeCreationStrategy;

public class ShapeCreationStrategyFactory {

    public static ShapeCreationStrategy getShapeCreationStrategy(ShapeType shapeType) {
        switch (shapeType) {
            case CIRCLE:
                return new CircleCreationStrategy();
            case LINE:
                return new LineCreationStrategy();
            case RECTANGLE:
                return new RectangleCreationStrategy();
            default:
                return new CircleCreationStrategy();
        }
    }
    
}
