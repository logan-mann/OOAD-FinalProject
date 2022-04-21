package com.naiflogan.finalproject.client.shapes;

import java.awt.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;

public class Rectangle extends Shape {
    private int length;
    private int height;
    private Coordinate center;


    public Rectangle(@JsonProperty("length")int length, @JsonProperty("height") int height, @JsonProperty("center")Coordinate center,
    @JsonProperty("hexColor") String hexColor) {
        this.center = center;
        this.height = height;
        this.length = length;
        this.hexColor = hexColor;
    }


    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Coordinate getCenter() {
        return this.center;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
    }


    public void draw(Graphics g, CanvasPanel canvas) {

        Dimension dim = canvas.getSize();
        int dimX = (int) dim.getWidth();
        int dimY = (int) dim.getHeight();
        int centerX = (dimX/CanvasPanel.DEFAULT_SIZE) * center.x;
        int centerY = (dimY/CanvasPanel.DEFAULT_SIZE) * center.y;

        int newLength = (dimX/CanvasPanel.DEFAULT_SIZE) * length;
        int newHeight = (dimY/CanvasPanel.DEFAULT_SIZE) * height;


        g.drawLine(centerX - newLength/2, centerY + newHeight/2, centerX + newLength/2, centerY + newHeight/2);
        g.drawLine(centerX - newLength/2, centerY - newHeight/2, centerX + newLength/2, centerY - newHeight/2);
        g.drawLine(centerX + newLength/2, centerY - newHeight/2, centerX + newLength/2, centerY + newHeight/2);
        g.drawLine(centerX - newLength/2, centerY - newHeight/2, centerX - newLength/2, centerY + newHeight/2);
    }

    public ShapeType getType() {
        return ShapeType.RECTANGLE;
    }

    public String toString() {
        return "Center: " + center + " Length: "+ length + "Height: " + height + " Color: " + hexColor;
    }
    
}
