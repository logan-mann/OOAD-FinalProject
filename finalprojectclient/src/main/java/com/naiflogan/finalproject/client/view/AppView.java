package com.naiflogan.finalproject.client.view;

import javax.swing.*;

import com.naiflogan.finalproject.client.controller.AuthController;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;

import java.awt.*;

/**
 * This class implements the main View that houses all the screens in the client application
 * Part of MVC pattern
 */
public class AppView extends JPanel implements View{

    //ClientModel object
    private ClientModel clientModel;
    //JPanel containing "cards" of all the application's screens, to be used w/ CardLayout
    private JPanel cards;

    //private enum of all view names, used to provide keys to CardLayout
    private static enum ViewName {
        AUTH_VIEW,
        HOMESCREEN_VIEW,
        CREATE_CANVAS_VIEW
    }

    public AppView(ClientModel clientModel, AuthController authController, HomescreenController homescreenController) {
        //Set clientModel and attach this view to clientModel
        this.clientModel = clientModel;
        clientModel.attach(this);

        //Create new AuthView
        AuthView authView = new AuthView(authController);
        //Create new HomescreenView, pass in clientModel and homescreenController
        HomescreenView homescreenView = new HomescreenView(clientModel, homescreenController);
        //Create new CreateCanvasView, pass in homescreenController
        CreateCanvasView createCanvasView = new CreateCanvasView(homescreenController);
        //Create new CardLayout panel to swap between views
        cards = new JPanel(new CardLayout());
        //Add all views with their respective tags
        cards.add(homescreenView, ViewName.HOMESCREEN_VIEW.name());
        cards.add(authView, ViewName.AUTH_VIEW.name());
        cards.add(createCanvasView, ViewName.CREATE_CANVAS_VIEW.name());
        //Add the card layout to the parent view
        this.add(cards, BorderLayout.CENTER);
        //Show correct view
        update();
    }

    //Private helper method to show view pertaining to viewName
    private void showView(ViewName viewName) {
        CardLayout cl = (CardLayout)(cards.getLayout()); 
        cl.show(cards, viewName.name());
    }

    @Override
    public void update() {
        //If user is logged in, show either CREATE_CANVAS_VIEW or HOMESCREEN_VIEW as clientModel says
        if (clientModel.isLoggedIn()) {
            if (clientModel.isOnCreateCanvasScreen()) {
                showView(ViewName.CREATE_CANVAS_VIEW);
            } else {
                showView(ViewName.HOMESCREEN_VIEW);
            }
        //Otherwise, show AUTH_VIEW so user can login/register
        } else {
            showView(ViewName.AUTH_VIEW);
        }
    }
    
}
