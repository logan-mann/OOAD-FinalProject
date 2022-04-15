package com.naiflogan.finalproject.client.shapes;

import java.awt.*;

public class Line extends Shape {

    private Coordinate start;
    private Coordinate end;

    public Line(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public void draw(Graphics g) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    public ShapeType getType() {
        return ShapeType.LINE;
    }
    
}
