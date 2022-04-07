package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "shapeType")
@JsonSubTypes({
    @JsonSubTypes.Type(value=Line.class, name = "LINE"),
    @JsonSubTypes.Type(value=Circle.class, name="CIRCLE"),
})
public abstract class Shape {
    public Shape() {

    }
    public abstract ShapeType getType();  
}

