package com.naiflogan.finalproject.client.view;

import javax.swing.*;

import com.naiflogan.finalproject.client.model.ClientModel;

public class HomescreenView extends JPanel implements View {

    ClientModel clientModel;

    public HomescreenView(ClientModel clientModel) {
        this.clientModel = clientModel;
        clientModel.attach(this);
        render();

    }

    private void render() {
        this.removeAll();
        this.add(new JTextArea(clientModel.getJwt()));
    }

    @Override
    public void update() {
        render();      
    }
    
}
