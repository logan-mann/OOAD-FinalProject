package com.naiflogan.finalproject.client.canvas;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naiflogan.finalproject.client.shapes.Shape;

public class Canvas {

    private List<Shape> shapes;
    private String name;
    private boolean isPublic;
    private Set<String> allowedUsernames;


    public Canvas(@JsonProperty("shapes") List<Shape> shapes,
     @JsonProperty("name")String name, 
     @JsonProperty("isPublic")boolean isPublic, 
     @JsonProperty("allowedUsernames")Set<String> allowedUsernames) {
        this.shapes = shapes;
        this.name = name;
        this.isPublic = isPublic;
        this.allowedUsernames = allowedUsernames;
    }

    public List<Shape> getShapes() {
        return this.shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsPublic() {
        return this.isPublic;
    }

    public boolean getIsPublic() {
        return this.isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Set<String> getAllowedUsernames() {
        return this.allowedUsernames;
    }

    public void setAllowedUsernames(Set<String> allowedUsernames) {
        this.allowedUsernames = allowedUsernames;
    }


}
