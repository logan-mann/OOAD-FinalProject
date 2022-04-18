package com.naiflogan.finalproject.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.user.User;
import com.naiflogan.finalproject.client.view.View;

public class ClientModel implements Model {

    private User user;
    private boolean loggedIn;

    private Canvas currentCanvas;
    private Map<String, Canvas> canvases;

    private List<View> attachedViews;

    public ClientModel() {
        this.user = null;
        this.loggedIn = false;
        this.currentCanvas = null;
        this.canvases = new HashMap<>();
        this.attachedViews = new ArrayList<>();
    }

    public Map<String, Canvas> getCanvases() {
        return canvases;
    }

    public void setCanvases(Map<String, Canvas> canvases) {
        this.canvases = canvases;
    }


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public boolean getLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Canvas getCurrentCanvas() {
        return this.currentCanvas;
    }

    public void setCurrentCanvas(Canvas currentCanvas) {
        this.currentCanvas = currentCanvas;
    }


    @Override
    public void attach(View view) {
        this.attachedViews.add(view);
    }

    @Override
    public void detach(View view) {
        this.attachedViews.remove(view);
    }

    @Override
    public void notifyViews() {
        for (View view : attachedViews) {
            view.update();
        }        
    }

    

    
}
