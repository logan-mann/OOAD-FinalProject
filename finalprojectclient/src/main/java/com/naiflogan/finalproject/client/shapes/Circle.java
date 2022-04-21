package com.naiflogan.finalproject.client.shapes;

import java.awt.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;

public class Circle extends Shape {

    private int radius;
    private Coordinate center;

    public Circle(@JsonProperty("radius") int radius, @JsonProperty("center")Coordinate center, @JsonProperty("hexColor") String hexColor) {
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

    public void draw(Graphics g, CanvasPanel canvas) {
        Dimension dim = canvas.getSize();
        double dimX = dim.getWidth();
        double dimY = dim.getHeight();
        int centerX = (int) (dimX/CanvasPanel.DEFAULT_SIZE * center.x);
        int centerY = (int) (dimY/CanvasPanel.DEFAULT_SIZE * center.y);

        int newRadius = (int) (dimX/CanvasPanel.DEFAULT_SIZE * radius);

        g.drawOval(centerX - newRadius, centerY - newRadius, newRadius*2, newRadius*2);
    }
    
}
