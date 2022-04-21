package com.naiflogan.finalproject.client.canvas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;

import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.shapes.Circle;
import com.naiflogan.finalproject.client.shapes.Coordinate;
import com.naiflogan.finalproject.client.shapes.Shape;
import com.naiflogan.finalproject.client.view.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CanvasPanel extends JPanel implements View {

    private List<Shape> shapes;

    private Shape hover;
    public final static int DEFAULT_SIZE = 500;
    private Dimension preferredSize = new Dimension(DEFAULT_SIZE,DEFAULT_SIZE);
    private ClientModel clientModel;

    private MouseInputListener mouseListener;



    public CanvasPanel(ClientModel clientModel) {
        this.setBackground(Color.WHITE);
        this.shapes = new ArrayList<>();
        this.hover = new Circle(0, new Coordinate(0, 0), "0x000000");
        this.setBorder(new LineBorder(Color.BLACK));
        this.clientModel = clientModel;
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    public CanvasPanel(List<Shape> shapes, ClientModel clientModel) {
        this.setBackground(Color.WHITE);
        this.hover = new Circle(0, new Coordinate(0, 0), "0x000000");
        this.shapes = shapes;
        this.setBorder(new LineBorder(Color.BLACK));
        this.clientModel = clientModel;
    }

    public void setPreferredSize(Dimension dim) {
        this.preferredSize = dim;
        this.repaint();
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : this.shapes) {
            g.setColor(Color.decode(shape.getHexColor()));
            shape.draw(g, this);
        }
        g.setColor(Color.decode(clientModel.getCurrentPenColor()));
        this.hover.draw(g, this);


    }

    public void setMouseListener(MouseInputListener newListener) {
        if (this.mouseListener != null) {
            this.removeMouseListener(this.mouseListener);
            this.removeMouseMotionListener(this.mouseListener);
        }
        this.mouseListener = newListener;
        this.addMouseListener(newListener);
        this.addMouseMotionListener(newListener);
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

    @Override
    public void update() {
        this.revalidate();
        this.repaint();
    }
    
}
