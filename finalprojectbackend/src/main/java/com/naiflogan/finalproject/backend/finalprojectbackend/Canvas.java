package com.naiflogan.finalproject.backend.finalprojectbackend;

import java.util.ArrayList;
import java.util.List;

import com.naiflogan.finalproject.backend.finalprojectbackend.shapes.Shape;

/**
 * Class containing all attributes of a Canvas
 * Contains a list of shapes, whether it's public, who can access it, and a name
 */
public class Canvas {

    List<Shape> shapes;
    boolean isPublic;
    List<String> allowedUsernames;
    String name;


    public Canvas(String name) {
        this.shapes = new ArrayList<Shape>();
        this.isPublic = true;
        this.allowedUsernames = new ArrayList<>();
    }

    public Canvas(String name, boolean isPublic, List<String> allowedUsernames) {
        this.shapes = new ArrayList<Shape>();
        this.isPublic = isPublic;
        this.allowedUsernames = allowedUsernames;   
        this.name = name; 
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public List<String> getAllowedUsernames() {
        return allowedUsernames;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getName() {
        return name;
    }
    
}
