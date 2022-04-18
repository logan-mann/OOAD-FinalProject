package com.naiflogan.finalproject.client.view;

import javax.swing.*;

import com.naiflogan.finalproject.client.canvas.CanvasPanel;
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
        if (clientModel.getUser() != null) {
            this.add(new JTextArea(clientModel.getUser().getUsername()));
        }
        if (clientModel.getCurrentCanvas() != null) {
            this.add(new CanvasPanel(clientModel.getCurrentCanvas().getShapes()));
        }
    }

    @Override
    public void update() {
        render();      
    }
    
}
