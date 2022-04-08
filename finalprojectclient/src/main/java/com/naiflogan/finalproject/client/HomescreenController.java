package com.naiflogan.finalproject.client;

import com.naiflogan.finalproject.client.shapes.Shape;

public class HomescreenController {
    private HomescreenModel model;
    private invoker commandInvoker;

    public HomescreenController(HomescreenModel model, invoker commandInvoker) {
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


}
