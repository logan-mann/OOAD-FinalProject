package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Extends abstract Shape class, is concrete class for "Circle" shape type
 */
public class Circle extends Shape {

    private double radius;
    private Coordinate center;

    
    public Circle(@JsonProperty("radius")double radius,@JsonProperty("center")Coordinate center,@JsonProperty("hexColor")String hexColor) {
        this.radius = radius;
        this.center = center;
        this.hexColor = hexColor;
    }

    public double getRadius() {
        return this.radius;
    }

    public Coordinate getCenter() {
        return this.center;
    }

    public String toString() {
        return "Center: " + center + " Radius: " + radius + " Hex Color: " + hexColor;
    }
    
}
