package com.naiflogan.finalproject.client.model;

import com.naiflogan.finalproject.client.view.View;

public interface Model {

    public void attach(View view);
    public void detach(View view);
    public void notifyViews();
    
}
