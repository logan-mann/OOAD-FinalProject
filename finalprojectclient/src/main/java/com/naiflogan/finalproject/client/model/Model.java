package com.naiflogan.finalproject.client.model;

import com.naiflogan.finalproject.client.view.View;

/**
 * Model interface for defining a model as part of MVC PATTERN
 */
public interface Model {

    public void attach(View view);
    public void detach(View view);
    public void notifyViews();
    
}
