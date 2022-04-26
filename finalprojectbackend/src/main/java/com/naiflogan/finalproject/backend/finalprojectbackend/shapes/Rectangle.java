package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Extends abstract Shape class, is concrete class for "Rectangle" shape type
 */
public class Rectangle extends Shape {

    @JsonProperty("length")
    private int length;

    @JsonProperty("height")
    private int height;

    @JsonProperty("center")
    private Coordinate center;

    public Rectangle(int length, int height, Coordinate center, String hexColor) {
        this.length = length;
        this.height = height;
        this.center = center;
        this.hexColor = hexColor;
    }

    public void setLength(int length) {
        this.length = length;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setCenter(Coordinate center) {
        this.center = center;
    }


    public double getLength() {
        return this.length;
    }

    public double getHeight() {
        return this.height;
    }

    public Coordinate getCenter() {
        return this.center;
    }

    public ShapeType getType() {
        return ShapeType.RECTANGLE;
    }

    public String toString() {
        return "Center: " + center + " Length: " + length + " Height: " + height + "Hex Color: " + hexColor;
    }
    
}
