package com.naiflogan.finalproject.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.naiflogan.finalproject.client.request.AddCanvasRequest;
import com.naiflogan.finalproject.client.request.AddShapeRequest;
import com.naiflogan.finalproject.client.request.CreateAccountRequest;
import com.naiflogan.finalproject.client.request.LoginRequest;
import com.naiflogan.finalproject.client.requestsender.AuthRequestSender;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.shapes.Circle;
import com.naiflogan.finalproject.client.shapes.Coordinate;
import com.naiflogan.finalproject.client.shapes.Line;
import com.naiflogan.finalproject.client.shapes.Rectangle;
import com.naiflogan.finalproject.client.view.AppView;
import com.naiflogan.finalproject.client.view.AuthView;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.AuthController;
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

    JPanel cards;
    
    public static void main(String[] args) {
        try {
            BackendRequestSender sender = BackendRequestSender.getInstance();
            AuthRequestSender authSender = AuthRequestSender.getInstance();
            CreateAccountRequest createAcc = new CreateAccountRequest("loganmann", "testPass579");
            authSender.createAccount(createAcc);
            LoginRequest login = new LoginRequest("loganmann", "testPass579");
            String jwt = authSender.login(login);
            
            Coordinate start = new Coordinate(5, 6);
            Coordinate end = new Coordinate(7,8);
            Line newLine = new Line(start, end);
            Circle newCircle = new Circle(5, new Coordinate(0,0));
            AddCanvasRequest request0 = new AddCanvasRequest("testCanvas", jwt, true);
            AddShapeRequest request1 = new AddShapeRequest(newLine, "testCanvas", jwt);
            AddShapeRequest request2 = new AddShapeRequest(newCircle, "testCanvas", jwt);
            sender.addCanvas(request0);
            sender.addShape(request1);
            sender.addShape(request2);
        } catch (Exception e) {

        }

        //AppView appView = new AppView();
        //appView.createAndShowGui();

        JFrame frame = new JFrame("CloudCanvas");
        ClientModel clientModel = new ClientModel();
        AuthController authController = new AuthController(clientModel);
        AppView appView = new AppView(clientModel, authController);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setContentPane(appView);
        frame.setVisible(true);
    }
}
