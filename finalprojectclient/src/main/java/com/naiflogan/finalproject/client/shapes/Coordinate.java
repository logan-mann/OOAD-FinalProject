package com.naiflogan.finalproject.client.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {

    public final int x;
    public final int y;

    public Coordinate(@JsonProperty("x") int x, 
    @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x: " + x + " y: " + y;
    }
    
}
