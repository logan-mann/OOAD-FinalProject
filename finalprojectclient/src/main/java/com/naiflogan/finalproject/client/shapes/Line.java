package com.naiflogan.finalproject.client.shapes;

import java.awt.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;

/**
 * Extends abstract Shape class, is concrete class for "Line" shape type
 */
public class Line extends Shape {

    //Start and end coordinates
    private Coordinate start;
    private Coordinate end;

    //Json property annotations for serializing responses from backend
    public Line(@JsonProperty("start") Coordinate start, 
                @JsonProperty("end")Coordinate end,
                @JsonProperty("hexColor") String hexColor) {
        this.start = start;
        this.end = end;
        this.hexColor = hexColor;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    //draw() implementation for Line shapes 
    public void draw(Graphics g, CanvasPanel canvas) {
        //Get dimensions of canvas
        Dimension dim = canvas.getPreferredSize();
        double dimX = dim.getWidth();
        double dimY = dim.getHeight();

        //Scale start and end coordinates to current canvas size relative to default canvas size
        int startX = (int) ((dimX/CanvasPanel.DEFAULT_SIZE) * start.x);
        int startY = (int) ((dimY/CanvasPanel.DEFAULT_SIZE) * start.y);

        int endX = (int) ((dimX/CanvasPanel.DEFAULT_SIZE) * end.x);
        int endY = (int) ((dimY/CanvasPanel.DEFAULT_SIZE) * end.y);



        //Draw a line from start to end
        g.drawLine(startX, startY, endX, endY);
    }
}
