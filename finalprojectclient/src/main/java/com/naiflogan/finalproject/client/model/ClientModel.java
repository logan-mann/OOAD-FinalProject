package com.naiflogan.finalproject.client.model;

import java.util.ArrayList;
import java.util.List;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.view.View;

public class ClientModel implements Model {

    private String jwt;
    private boolean loggedIn;

    private Canvas currentCanvas;

    private List<View> attachedViews;

    public ClientModel() {
        this.jwt = "";
        this.loggedIn = false;
        this.currentCanvas = new Canvas();
        this.attachedViews = new ArrayList<>();
    }


    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
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
