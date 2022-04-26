package com.naiflogan.finalproject.client;

import javax.swing.*;

import com.naiflogan.finalproject.client.view.AppView;

import com.naiflogan.finalproject.client.controller.AuthController;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;

/**
 * Hello world!
 */
public final class App {


    
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    
    public static void main(String[] args) {

        JFrame frame = new JFrame("CloudCanvas");
        ClientModel clientModel = new ClientModel();
        AuthController authController = new AuthController(clientModel);
        HomescreenController homescreenController = new HomescreenController(clientModel);
        AppView appView = new AppView(clientModel, authController, homescreenController);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000,800);
        frame.setContentPane(appView);
        frame.setVisible(true);

        while (true) {
            try {
                Thread.sleep(2000);

            } catch (Exception e) {

            }
            homescreenController.updateCanvasState();
        }
    }
}
