package com.naiflogan.finalproject.client.shapes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.awt.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "shapeType")
@JsonSubTypes({
    @JsonSubTypes.Type(value=Line.class, name = "LINE"),
    @JsonSubTypes.Type(value=Circle.class, name="CIRCLE"),
    @JsonSubTypes.Type(value=Rectangle.class, name="RECTANGLE")
})
public abstract class Shape {


    public Shape() {
    }

    public abstract void draw(Graphics g);

    public abstract ShapeType getType(); 
}