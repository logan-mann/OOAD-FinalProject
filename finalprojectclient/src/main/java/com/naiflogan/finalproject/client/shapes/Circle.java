package com.naiflogan.finalproject.client.shapes;

import java.awt.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;

/**
 * Extends abstract Shape class, is concrete class for "Circle" shape type
 */
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

    //draw() implementation for circle
    public void draw(Graphics g, CanvasPanel canvas) {
        //Get dimensions of the canvas being drawn on
        Dimension dim = canvas.getSize();
        double dimX = dim.getWidth();
        double dimY = dim.getHeight();
        //Scale center coordinates based on dimensions of canvas relative to default canvas size
        int centerX = (int) (dimX/CanvasPanel.DEFAULT_SIZE * center.x);
        int centerY = (int) (dimY/CanvasPanel.DEFAULT_SIZE * center.y);

        //Scale radius based on dimensions of current canvas relative to default canvas size
        int newRadius = (int) (dimX/CanvasPanel.DEFAULT_SIZE * radius);

        //Draw an oval on canvas centered at this position with radius calculated
        g.drawOval(centerX - newRadius, centerY - newRadius, newRadius*2, newRadius*2);
    }
    
}
