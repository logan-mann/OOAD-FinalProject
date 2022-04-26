package com.naiflogan.finalproject.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.shapes.ColorConstants;
import com.naiflogan.finalproject.client.shapes.ShapeType;
import com.naiflogan.finalproject.client.strategy.LineCreationStrategy;
import com.naiflogan.finalproject.client.strategy.ShapeCreationStrategy;
import com.naiflogan.finalproject.client.user.User;
import com.naiflogan.finalproject.client.view.View;

/**
 * ClientModel is the main model object that holds the state of the client application
 * Contains key information about the logged in user, current canvas state, etc.
 * Part of MVC PATTERN
 */
public class ClientModel implements Model {

    //Logged in User object
    private User user;
    //Boolean representing whether a user is currently logged in, used for deciding which views to render
    private boolean loggedIn;

    //Current canvas object that the user is modifying/viewing on the main canvas view
    private Canvas currentCanvas;
    //Contains all potential canvases available to user, key->value is canvasName->canvasObject
    private Map<String, Canvas> canvases;

    //ShapeType enum value represnting current shape type user is placing on canvas
    private ShapeType currentShapeType;
    //ShapeCreationStrategy for current ShapeType
    private ShapeCreationStrategy shapeCreationStrategy;

    //State variable to determine whether user is currently on canvas creation screen, or main view
    private boolean isOnCreateCanvasScreen;

    //Current pen color, must be hex string of the pattern 0xRRGGBB
    private String currentPenColor;

    //List of views subscribed to a given model object
    private List<View> attachedViews;

    public ClientModel() {
        this.user = null;
        this.loggedIn = false;
        this.currentCanvas = null;
        this.canvases = new HashMap<>();
        this.attachedViews = new ArrayList<>();
        this.currentShapeType = ShapeType.LINE;
        this.shapeCreationStrategy = new LineCreationStrategy();
        this.currentPenColor = ColorConstants.RED;
        this.isOnCreateCanvasScreen = false;
    }

    public ShapeType getCurrentShapeType() {
        return currentShapeType;
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

    public boolean isOnCreateCanvasScreen() {
        return isOnCreateCanvasScreen;
    }

    public void setIsOnCreateCanvasScreen(boolean isOnCreateCanvasScreen) {
        this.isOnCreateCanvasScreen = isOnCreateCanvasScreen; 
    }

    public void setCurrentPenColor(String hexColor) {
        this.currentPenColor = hexColor;
    }
    
    public String getCurrentPenColor() {
        return this.currentPenColor;
    }

    public   Map<String, Canvas> getCanvases() {
        return canvases;
    }

    public   void setCanvases(Map<String, Canvas> canvases) {
        this.canvases = canvases;
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

    public   void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Canvas getCurrentCanvas() {
        return this.currentCanvas;
    }

    public void setCurrentCanvas(String canvasName) {
        //If the list of canvases contains one of the name passed, set currentCanvas to that canvas
        if (canvases.containsKey(canvasName)){
            this.currentCanvas = canvases.get(canvasName);
        }
    }

    //Update canvas state
    public void updateCanvasState(Map<String, Canvas> newCanvasMap) {
        this.canvases = newCanvasMap;
        if (currentCanvas != null) {
            //If new canvas state contains canvas of current name, set currentCanvas to this value
            this.currentCanvas = newCanvasMap.getOrDefault(currentCanvas.getName(), null);
        } else {
            //Otherwise, choose a random canvas if available, or if not set currentCanvas to null
            if (this.canvases.values().size() > 0) {

                this.currentCanvas = this.canvases.values().iterator().next();
            } else {

                this.currentCanvas = null;
            }
        }
    }


    //Attach a new view
    @Override
    public void attach(View view) {
        attachedViews.add(view);
    }

    //Remove a subscribed view
    @Override
    public void detach(View view) {
        attachedViews.remove(view);
    }

    //Notify all subscribed views
    @Override
    public void notifyViews() {
        List<View> views = new ArrayList<>(attachedViews);
        for (View view : views) {
            view.update();
        }
    }

    

    
}
