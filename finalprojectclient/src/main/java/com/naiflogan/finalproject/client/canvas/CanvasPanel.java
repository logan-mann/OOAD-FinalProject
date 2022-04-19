package com.naiflogan.finalproject.client.canvas;

import javax.swing.*;
import javax.swing.border.LineBorder;

import com.naiflogan.finalproject.client.shapes.Circle;
import com.naiflogan.finalproject.client.shapes.Coordinate;
import com.naiflogan.finalproject.client.shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CanvasPanel extends JPanel {

    private List<Shape> shapes;

    private int radius = 15;
    private Shape hover;
    public final static int DEFAULT_SIZE = 500;
    private Dimension preferredSize = new Dimension(DEFAULT_SIZE,DEFAULT_SIZE);



    public CanvasPanel() {
        this.setBackground(Color.WHITE);
        this.shapes = new ArrayList<>();
        this.hover = new Circle(radius, new Coordinate(0, 0));
        //this.addMouseMotionListener(this);
        this.setBorder(new LineBorder(Color.BLACK));
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    public CanvasPanel(List<Shape> shapes) {
        this.setBackground(Color.WHITE);
        this.hover = new Circle(radius, new Coordinate(0, 0));
        this.shapes = shapes;
        //this.addMouseMotionListener(this);
        this.setBorder(new LineBorder(Color.BLACK));

    }

    public void setPreferredSize(Dimension dim) {
        this.preferredSize = dim;
        this.repaint();
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        super.paintComponent(g);

        for (Shape shape : this.shapes) {
            shape.draw(g, this);
        }

        this.hover.draw(g, this);


    }

    public void setHover(Shape hover) {
        this.hover = hover;
        this.revalidate();
        this.repaint();
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
        revalidate();
        repaint();
    }
    
}
