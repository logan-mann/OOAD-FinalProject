package com.naiflogan.finalproject.client;

import javax.swing.*;

import com.naiflogan.finalproject.client.view.AppView;

import com.naiflogan.finalproject.client.controller.AuthController;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;

/**
 * Driver class for client application
 */
public final class App {


    
    private App() {
    }
    
    //Instantiates and runs the client application
    public static void main(String[] args) {

        //Create a window
        JFrame frame = new JFrame("CloudCanvas");
        //Create a fresh model object
        ClientModel clientModel = new ClientModel();
        //Create AuthController and pass in model
        AuthController authController = new AuthController(clientModel);
        //Create HomescreenController and pass in model
        HomescreenController homescreenController = new HomescreenController(clientModel);
        //Create AppView (Which houses rest of application), and pass in model and controllers
        AppView appView = new AppView(clientModel, authController, homescreenController);
        //Configure window, set size, etc.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000,800);
        frame.setContentPane(appView);
        frame.setVisible(true);
        //Poll backend for canvas updates every 2 seconds while application is running
        while (true) {
            try {
                Thread.sleep(2000);

            } catch (Exception e) {

            }
            homescreenController.updateCanvasState();
        }
    }
}
