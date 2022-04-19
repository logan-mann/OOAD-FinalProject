package com.naiflogan.finalproject.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.shapes.ShapeType;
import com.naiflogan.finalproject.client.strategy.CircleCreationStrategy;
import com.naiflogan.finalproject.client.strategy.ShapeCreationStrategy;
import com.naiflogan.finalproject.client.user.User;
import com.naiflogan.finalproject.client.view.View;

public class ClientModel implements Model {

    private User user;
    private boolean loggedIn;

    private Canvas currentCanvas;
    private Map<String, Canvas> canvases;

    private List<View> attachedViews;

    private ShapeType currentShapeType;
    private ShapeCreationStrategy shapeCreationStrategy;

    public List<View> getAttachedViews() {
        return this.attachedViews;
    }

    public void setAttachedViews(List<View> attachedViews) {
        this.attachedViews = attachedViews;
    }
    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
    }

    public ShapeCreationStrategy getShapeCreationStrategy() {
        return this.shapeCreationStrategy;
    }

    public void setShapeCreationStrategy(ShapeCreationStrategy shapeCreationStrategy) {
        this.shapeCreationStrategy = shapeCreationStrategy;
    }

    public ClientModel() {
        this.user = null;
        this.loggedIn = false;
        this.currentCanvas = null;
        this.canvases = new HashMap<>();
        this.attachedViews = new ArrayList<>();
        this.currentShapeType = ShapeType.Circle;
        this.shapeCreationStrategy = new CircleCreationStrategy();
    }

    public   Map<String, Canvas> getCanvases() {
        return canvases;
    }

    public   void setCanvases(Map<String, Canvas> canvases) {
        this.canvases = canvases;
    }

    public void setShapeType(ShapeType type) {
        this.currentShapeType = type;
    }

    public ShapeType getCurrentShapeType() {
        return currentShapeType;
    }


    public   User getUser() {
        return this.user;
    }

    public   void setUser(User user) {
        this.user = user;
    }

    public   boolean isLoggedIn() {
        return this.loggedIn;
    }

    public   boolean getLoggedIn() {
        return this.loggedIn;
    }

    public   void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Canvas getCurrentCanvas() {
        return this.currentCanvas;
    }

    public void setCurrentCanvas(String canvasName) {
        if (canvases.containsKey(canvasName)){
            this.currentCanvas = canvases.get(canvasName);
        }
    }

    public void updateCanvasState(Map<String, Canvas> newCanvasMap) {
        this.canvases = newCanvasMap;
        this.currentCanvas = newCanvasMap.get(currentCanvas.getName());
    }


    @Override
    public void attach(View view) {
        attachedViews.add(view);
    }

    @Override
    public void detach(View view) {
        attachedViews.remove(view);
    }

    @Override
    public void notifyViews() {
        List<View> views = new ArrayList<>(attachedViews);
        for (View view : views) {
            view.update();
        }
        System.out.println(views.size());
    }

    

    
}
