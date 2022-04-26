package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Extends abstract Shape class, is concrete class for "Line" shape type
 */
public class Line extends Shape {

    private Coordinate start;
    private Coordinate end;

    public Line(@JsonProperty("start")Coordinate start,@JsonProperty("end")Coordinate end) {
        this.start = start;
        this.end = end;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }
    
}
