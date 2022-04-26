package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstract class for shapes that will be drawn on canvases
 * JsonTypeInfo and subsequent annotations are used to keep polymorphism viable between client and server
 * Adds context field "shapeType" to request/response bodies, allowing us to deserialize requests/responses into appropriate concrete shape classes
 * No draw method here because backend doesn't need it
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "shapeType")
@JsonSubTypes({
    @JsonSubTypes.Type(value=Line.class, name = "LINE"),
    @JsonSubTypes.Type(value=Circle.class, name="CIRCLE"),
    @JsonSubTypes.Type(value=Rectangle.class, name="RECTANGLE")
})
public abstract class Shape {
    public Shape() {

    }

    protected String hexColor;

    public String getHexColor() {
        return hexColor;
    }
}

