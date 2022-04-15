package com.naiflogan.finalproject.client.canvas;

import javax.swing.*;

import com.naiflogan.finalproject.client.shapes.Circle;
import com.naiflogan.finalproject.client.shapes.Coordinate;
import com.naiflogan.finalproject.client.shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class Canvas extends JPanel implements MouseMotionListener {

    private List<Shape> shapes;

    private int radius = 15;
    private Shape hover;


    public Canvas() {
        this.setBackground(Color.WHITE);
        this.shapes = new ArrayList<>();
        this.hover = new Circle(0, new Coordinate(0, 0));
        this.addMouseMotionListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    public Canvas(List<Shape> shapes) {
        this.setBackground(Color.WHITE);
        this.shapes = shapes;
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        super.paintComponent(g);

        for (Shape shape : this.shapes) {
            shape.draw(g);
        }

        this.hover.draw(g);


    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        int x = arg0.getX();
        int y = arg0.getY();

        this.hover = new Circle(radius, new Coordinate(x, y));
        this.repaint();
        
    }
    
}
