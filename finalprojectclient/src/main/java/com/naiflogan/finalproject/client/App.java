package com.naiflogan.finalproject.client;

import com.naiflogan.finalproject.client.requests.AddCanvasRequest;
import com.naiflogan.finalproject.client.requests.AddShapeRequest;
import com.naiflogan.finalproject.client.shapes.Circle;
import com.naiflogan.finalproject.client.shapes.Coordinate;
import com.naiflogan.finalproject.client.shapes.Line;

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
        try {
            Coordinate start = new Coordinate(5, 6);
            Coordinate end = new Coordinate(7,8);
            Line newLine = new Line(start, end);
            Circle newCircle = new Circle(5, new Coordinate(0,0));
            BackendRequestSender sender = BackendRequestSender.getInstance();
            AddCanvasRequest request0 = new AddCanvasRequest("testCanvas", "LoganMann", true);
            AddShapeRequest request1 = new AddShapeRequest(newLine, "testCanvas", "LoganMann");
            AddShapeRequest request2 = new AddShapeRequest(newCircle, "testCanvas", "LoganMann");
            sender.addCanvas(request0);
            sender.addShape(request1);
            sender.addShape(request2);
        } catch (Exception e) {

        }

    }
}
