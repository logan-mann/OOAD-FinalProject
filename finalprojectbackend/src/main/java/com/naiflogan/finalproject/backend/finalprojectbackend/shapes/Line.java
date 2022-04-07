package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

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

    public ShapeType getType() {
        return ShapeType.LINE;
    }
    
}
