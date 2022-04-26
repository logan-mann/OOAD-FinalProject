package com.naiflogan.finalproject.backend.finalprojectbackend.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class containing (x,y) coordinates, used to place shapes on canvas
 */
public class Coordinate {

    @JsonProperty("x")
    public final int x;
    @JsonProperty("y")
    public final int y;

    public Coordinate(@JsonProperty("x")int x,@JsonProperty("y")int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x: " + x + " y: " + y;
    }

    
    
}
