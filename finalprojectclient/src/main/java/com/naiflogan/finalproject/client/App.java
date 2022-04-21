package com.naiflogan.finalproject.client;

import javax.swing.*;

import com.naiflogan.finalproject.client.request.AddCanvasRequest;
import com.naiflogan.finalproject.client.request.AddShapeRequest;
import com.naiflogan.finalproject.client.request.CreateAccountRequest;
import com.naiflogan.finalproject.client.request.LoginRequest;
import com.naiflogan.finalproject.client.requestsender.AuthRequestSender;
import com.naiflogan.finalproject.client.requestsender.BackendRequestSender;
import com.naiflogan.finalproject.client.shapes.Circle;
import com.naiflogan.finalproject.client.shapes.ColorConstants;
import com.naiflogan.finalproject.client.shapes.Coordinate;
import com.naiflogan.finalproject.client.shapes.Line;
import com.naiflogan.finalproject.client.user.User;
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
            BackendRequestSender sender = BackendRequestSender.getInstance();
            AuthRequestSender authSender = AuthRequestSender.getInstance();
            CreateAccountRequest createAcc = new CreateAccountRequest("loganmann", "testPass579");
            authSender.createAccount(createAcc);
            LoginRequest login = new LoginRequest("loganmann", "testPass579");
            User user = authSender.login(login);
            
            Coordinate start = new Coordinate(100, 100);
            Coordinate end = new Coordinate(300,300);
            Line newLine = new Line(start, end, ColorConstants.PURPLE);
            Circle newCircle = new Circle(50, new Coordinate(100,100), ColorConstants.BLUE);
            Circle secondCircle = new Circle(150, new Coordinate(200, 250), ColorConstants.RED);
            AddCanvasRequest request0 = new AddCanvasRequest("testCanvas", user.getJsonWebToken(), true);
            AddCanvasRequest request3 = new AddCanvasRequest("testCanvas2", user.getJsonWebToken(), true);
            AddShapeRequest request1 = new AddShapeRequest(newLine, "testCanvas", user.getJsonWebToken());
            AddShapeRequest request2 = new AddShapeRequest(newCircle, "testCanvas", user.getJsonWebToken());
            AddShapeRequest request4 = new AddShapeRequest(secondCircle, "testCanvas2", user.getJsonWebToken());

            sender.addCanvas(request0);
            sender.addCanvas(request3);
            sender.addShape(request4);
            sender.addShape(request1);
            sender.addShape(request2);

        //AppView appView = new AppView();
        //appView.createAndShowGui();

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
