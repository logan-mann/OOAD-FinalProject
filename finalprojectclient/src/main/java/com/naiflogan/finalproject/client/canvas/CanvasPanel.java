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


/**
 * View class for a canvas, contains mouse listener for handling shape creation, animating where shape will be placed, etc.
 * Is essentially a white JPanel, calls draw function of each shape in shapes to draw them on component
 */
public class CanvasPanel extends JPanel implements View {

    //List of shapes to be drawn on panel
    private List<Shape> shapes;

    //Shape object drawn at mouse location to show where shape will be placed on canvas when moouse clicked
    private Shape hover;
    //Constant for default component size
    public final static int DEFAULT_SIZE = 500;
    //init preferred size to default
    private Dimension preferredSize = new Dimension(DEFAULT_SIZE,DEFAULT_SIZE);
    //Client model for the panel
    private ClientModel clientModel;
    //Mouse listener to handle animation/shape placement
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
        //Iterate over shapes
        for (Shape shape : this.shapes) {
            //Set pen color to curr shape's color, draw the shape
            g.setColor(Color.decode(shape.getHexColor()));
            shape.draw(g, this);
        }
        //Set color to clientModels current pen color, draw hover shape
        g.setColor(Color.decode(clientModel.getCurrentPenColor()));
        this.hover.draw(g, this);
    }

    //Function for changing mouse listener
    public void setMouseListener(MouseInputListener newListener) {
        //If mouseListener isn't null, remove old mouse listener before adding new one
        if (this.mouseListener != null) {
            this.removeMouseListener(this.mouseListener);
            this.removeMouseMotionListener(this.mouseListener);
        }
        //set mouseListener and add it as listener to the component
        this.mouseListener = newListener;
        this.addMouseListener(newListener);
        this.addMouseMotionListener(newListener);
    }

    //Update hover and repaint the component
    public void setHover(Shape hover) {
        this.hover = hover;
        this.revalidate();
        this.repaint();
    }

    //Update shape list and repaint component
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
