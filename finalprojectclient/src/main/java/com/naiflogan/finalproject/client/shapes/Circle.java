package com.naiflogan.finalproject.client.shapes;

import java.awt.*;

public class Circle extends Shape {

    private int radius;
    private Coordinate center;

    public Circle(int radius, Coordinate center) {
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

    public void draw(Graphics g) {
        g.drawOval(center.x - radius, center.y - radius, radius*2, radius*2);
    }
    
}
