package com.naiflogan.finalproject.client.strategy;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.shapes.Circle;
import com.naiflogan.finalproject.client.shapes.Coordinate;

public class CircleCreationStrategy implements ShapeCreationStrategy {

    private int radius;

    private Coordinate lastMouseLocation = new Coordinate(0, 0);

    public CircleCreationStrategy() {
        this.radius = 50;
    }

    @Override
    public JPanel getShapePropertiesMenu(CanvasPanel canvasPanel) {

        JLabel radiusDisplay = new JLabel("Radius: " + radius);

        JPanel propertiesMenu = new JPanel();
        propertiesMenu.setLayout(new BoxLayout(propertiesMenu, BoxLayout.Y_AXIS));
        JPanel radiusPanel = new JPanel();
        radiusPanel.setLayout(new BoxLayout(radiusPanel, BoxLayout.Y_AXIS));
        JButton decreaseRadiusButton = new JButton("Decrement Radius");
        JButton increaseRadiusButton = new JButton("Increment Radius");
        decreaseRadiusButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (radius > 5) {
                    radius-=5;
                    Circle newCircle = new Circle(radius, lastMouseLocation);
                    canvasPanel.setHover(newCircle);
                    radiusDisplay.setText(getRadiusLabelText());
                    radiusDisplay.revalidate();
                    radiusDisplay.repaint();
                }
            }
            
        });
        increaseRadiusButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                radius+= 5;
                Circle newCircle = new Circle(radius, lastMouseLocation);
                canvasPanel.setHover(newCircle);
                radiusDisplay.setText(getRadiusLabelText());
                radiusDisplay.revalidate();
                radiusDisplay.repaint();
            }
            
        });
        propertiesMenu.add(new JLabel("Current Shape Type: Circle"), Component.TOP_ALIGNMENT);
        radiusPanel.add(increaseRadiusButton);
        radiusPanel.add(radiusDisplay);
        radiusPanel.add(decreaseRadiusButton);
        propertiesMenu.add(radiusPanel);
        return propertiesMenu;
    }

    private String getRadiusLabelText() {
        return "Radius: " + radius;
    }

    @Override
    public MouseInputListener getShapeCreationListener(CanvasPanel canvasPanel, HomescreenController homescreenController) {
        MouseInputListener listener = new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                Coordinate center = new Coordinate(arg0.getX(), arg0.getY());
                Circle newCircle = new Circle(radius, center);
                homescreenController.placeShape(newCircle);                
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                lastMouseLocation = new Coordinate(arg0.getX(), arg0.getY());
                
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseDragged(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseMoved(MouseEvent arg0) {
                Coordinate center = new Coordinate(arg0.getX(), arg0.getY());
                Circle newHoverCircle = new Circle(radius, center);
                canvasPanel.setHover(newHoverCircle);
            }
            
        };
        return listener;
    }
}