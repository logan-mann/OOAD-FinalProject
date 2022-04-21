package com.naiflogan.finalproject.client.shapes;

import java.awt.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;

public class Line extends Shape {

    private Coordinate start;
    private Coordinate end;

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

    public void draw(Graphics g, CanvasPanel canvas) {
        Dimension dim = canvas.getPreferredSize();
        double dimX = dim.getWidth();
        double dimY = dim.getHeight();

        int startX = (int) ((dimX/CanvasPanel.DEFAULT_SIZE) * start.x);
        int startY = (int) ((dimY/CanvasPanel.DEFAULT_SIZE) * start.y);

        int endX = (int) ((dimX/CanvasPanel.DEFAULT_SIZE) * end.x);
        int endY = (int) ((dimY/CanvasPanel.DEFAULT_SIZE) * end.y);




        g.drawLine(startX, startY, endX, endY);
    }
}
