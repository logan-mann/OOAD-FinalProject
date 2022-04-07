package com.naiflogan.finalproject.backend.finalprojectbackend.requests;

import com.naiflogan.finalproject.backend.finalprojectbackend.shapes.Shape;

public class AddShapeRequest {
    private Shape shape;
    private String canvasName;

    //TODO - change this to be a JWT rather than username
    private String username;

    public AddShapeRequest(Shape shape, String canvasName, String username) {
        this.shape = shape;
        this.canvasName = canvasName;
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }


    public Shape getShape() {
        return this.shape;
    }

    public String getCanvasName() {
        return this.canvasName;
    }
    
}
