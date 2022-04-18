package com.naiflogan.finalproject.client.shapes;

import java.awt.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rectangle extends Shape {
    private Coordinate center;
    private int height;
    private int length;

    public Rectangle(@JsonProperty("center")Coordinate center,@JsonProperty("length")int length,@JsonProperty("height")int height) {
        this.center = center;
        this.height = height;
        this.length = length;
    }

    public void draw(Graphics g) {
        g.drawLine(center.x - length/2, center.y + height/2, center.x + length/2, center.y + height/2);
        g.drawLine(center.x - length/2, center.y - height/2, center.x + length/2, center.y - height/2);
        g.drawLine(center.x + length/2, center.y - height/2, center.x + length/2, center.y + height/2);
        g.drawLine(center.x - length/2, center.y - height/2, center.x - length/2, center.y + height/2);
    }

    public ShapeType getType() {
        return ShapeType.RECTANGLE;
    }
    
}
