package com.naiflogan.finalproject.backend.finalprojectbackend.request;

import com.naiflogan.finalproject.backend.finalprojectbackend.shapes.Shape;

public class AddShapeRequest {
    private Shape shape;
    private String canvasName;
    private String jwt;

    public AddShapeRequest(Shape shape, String canvasName, String jwt) {
        this.shape = shape;
        this.canvasName = canvasName;
        this.jwt = jwt;
    }

    public String getJwt() {
        return this.jwt;
    }


    public Shape getShape() {
        return this.shape;
    }

    public String getCanvasName() {
        return this.canvasName;
    }
    
}
