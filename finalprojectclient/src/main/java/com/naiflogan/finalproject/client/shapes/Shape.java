package com.naiflogan.finalproject.client.shapes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;

import java.awt.*;

/**
 * Abstract class for shapes that will be drawn on canvases
 * JsonTypeInfo and subsequent annotations are used to keep polymorphism viable between client and server
 * Adds context field "shapeType" to request/response bodies, allowing us to deserialize requests/responses into appropriate concrete shape classes
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "shapeType")
@JsonSubTypes({
    @JsonSubTypes.Type(value=Line.class, name = "LINE"),
    @JsonSubTypes.Type(value=Circle.class, name="CIRCLE"),
    @JsonSubTypes.Type(value=Rectangle.class, name="RECTANGLE")
})
public abstract class Shape {

    //Pen color used to draw shape
    protected String hexColor;

    //Method declaration to draw a shape, takes in a CanvasPanel and Graphics objects (Graphics object is from JPanel, retrieved when calling CanvasPanel's paintComponent())
    public abstract void draw(Graphics g, CanvasPanel canvas);

    public String getHexColor() {
        return hexColor;
    }
}