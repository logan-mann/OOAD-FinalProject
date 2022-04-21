package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {

    @JsonProperty("x")
    public final int x;
    @JsonProperty("y")
    public final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x: " + x + " y: " + y;
    }

    
    
}
