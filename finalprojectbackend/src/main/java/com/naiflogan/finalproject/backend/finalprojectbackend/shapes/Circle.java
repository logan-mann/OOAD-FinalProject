package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

/**
 * Extends abstract Shape class, is concrete class for "Circle" shape type
 */
public class Circle extends Shape {

    private double radius;
    private Coordinate center;

    
    public Circle(double radius, Coordinate center, String hexColor) {
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

    public ShapeType getType() {
        return ShapeType.CIRCLE;
    }

    public String toString() {
        return "Center: " + center + " Radius: " + radius + " Hex Color: " + hexColor;
    }
    
}
