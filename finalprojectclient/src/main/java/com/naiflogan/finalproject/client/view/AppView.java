package com.naiflogan.finalproject.client.view;

import javax.swing.*;

import com.naiflogan.finalproject.client.controller.AuthController;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;

import java.awt.*;

public class AppView extends JPanel implements View{


    private ClientModel clientModel;
    private JPanel cards;


    private static enum ViewName {
        AUTH_VIEW,
        HOMESCREEN_VIEW
    }


    public AppView(ClientModel clientModel, AuthController authController, HomescreenController homescreenController) {
        this.clientModel = clientModel;
        clientModel.attach(this);

        AuthView authView = new AuthView(authController);
        HomescreenView homescreenView = new HomescreenView(clientModel, homescreenController);
        cards = new JPanel(new CardLayout());
        cards.add(homescreenView, ViewName.HOMESCREEN_VIEW.name());
        cards.add(authView, ViewName.AUTH_VIEW.name());
        this.add(cards, BorderLayout.CENTER);
        update();
    }

    private void showView(ViewName viewName) {
        CardLayout cl = (CardLayout)(cards.getLayout()); 
        cl.show(cards, viewName.name());
    }

    @Override
    public void update() {
        if (clientModel.isLoggedIn()) {
            showView(ViewName.HOMESCREEN_VIEW);
        } else {
            showView(ViewName.AUTH_VIEW);
        }
    }
    
}
