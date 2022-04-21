package com.naiflogan.finalproject.client.shapes;

import java.awt.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;

public class Rectangle extends Shape {
    private Coordinate center;
    private int height;
    private int length;

    public Rectangle(@JsonProperty("center")Coordinate center,@JsonProperty("length")int length,@JsonProperty("height")int height,
    @JsonProperty("hexColor") String hexColor) {
        this.center = center;
        this.height = height;
        this.length = length;
        this.hexColor = hexColor;
    }

    public void draw(Graphics g, CanvasPanel canvas) {
        Dimension dim = canvas.getSize();
        System.out.println(dim.getWidth() + " " + dim.getHeight());
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
        return ShapeType.Rectangle;
    }
    
}
