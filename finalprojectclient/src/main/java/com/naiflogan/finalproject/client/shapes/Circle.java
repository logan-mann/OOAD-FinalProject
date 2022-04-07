package com.naiflogan.finalproject.client.shapes;

public class Circle extends Shape {

    private double radius;
    private Coordinate center;

    public Circle(double radius, Coordinate center) {
        this.radius = radius;
        this.center = center;
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
    
}
