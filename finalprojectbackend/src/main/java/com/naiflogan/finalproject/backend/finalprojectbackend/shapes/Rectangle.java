package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

public class Rectangle extends Shape {
    private double width;
    private double length;
    private Coordinate center;

    public Rectangle(double width, double length, Coordinate center) {
        this.width = width;
        this.length = length;
        this.center = center;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public Coordinate getCenter() {
        return center;
    }
    @Override
    public ShapeType getType() {
        return ShapeType.RECTANGLE;
    }
}
