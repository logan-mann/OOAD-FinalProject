package com.naiflogan.finalproject.client.strategy;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.shapes.Circle;
import com.naiflogan.finalproject.client.shapes.Coordinate;

/**
 * Implementation of ShapeCreationStrategy for Circles, part of STRATEGY PATTERN
 */
public class CircleCreationStrategy implements ShapeCreationStrategy {

    private int radius;

    private Coordinate lastMouseLocation = new Coordinate(0, 0);

    public CircleCreationStrategy() {
        this.radius = 50;
    }

    //This builds and returns a JPanel containing the properties menu for rectangles that will be displayed on main application screen
    @Override
    public JPanel getShapePropertiesMenu(CanvasPanel canvasPanel, HomescreenController homescreenController) {

        //Create label for radius
        JLabel radiusDisplay = new JLabel("Radius: " + radius);

        //Create JPanel to house menu
        JPanel propertiesMenu = new JPanel();
        propertiesMenu.setPreferredSize(new Dimension(180,500));
        propertiesMenu.setLayout(new BoxLayout(propertiesMenu, BoxLayout.Y_AXIS));
        
        //Panel for radius controls
        JPanel radiusPanel = new JPanel();
        radiusPanel.setLayout(new BoxLayout(radiusPanel, BoxLayout.Y_AXIS));
        //Buttons to increase and decrease radius
        JButton decreaseRadiusButton = new JButton("-");
        JButton increaseRadiusButton = new JButton("+");
        //Add action listener to button to increment/decrement radius as appropriate
        decreaseRadiusButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (radius > 5) {
                    radius-=5;
                    //Create new circle and update canvasPanel's hover shape
                    Circle newCircle = new Circle(radius, lastMouseLocation, homescreenController.getClientModelPenColor());
                    canvasPanel.setHover(newCircle);
                    //Update display text
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
                Circle newCircle = new Circle(radius, lastMouseLocation, homescreenController.getClientModelPenColor());
                canvasPanel.setHover(newCircle);
                radiusDisplay.setText(getRadiusLabelText());
                radiusDisplay.revalidate();
                radiusDisplay.repaint();
            }
            
        });

        //Add header to menu
        propertiesMenu.add(new JLabel("Current Shape Type: Circle"), Component.TOP_ALIGNMENT);
        propertiesMenu.add(Box.createVerticalStrut(25));
        //Add elements to radius controll panel and add it to main menu panel
        radiusPanel.add(increaseRadiusButton);
        radiusPanel.add(radiusDisplay);
        radiusPanel.add(decreaseRadiusButton);
        propertiesMenu.add(radiusPanel);
        //return contstructed menu component
        return propertiesMenu;
    }

    private String getRadiusLabelText() {
        return "Radius: " + radius;
    }

    //Implementation to create mouse listener for circle shape creation, listener returned here should be added as a listener to canvasPanel
    @Override
    public MouseInputListener getShapeCreationListener(CanvasPanel canvasPanel, HomescreenController homescreenController) {
        MouseInputListener listener = new MouseInputAdapter() {

            //If mouse is clicked, we want to place a circle at the current mouse location
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //Build coordinate object for location of mouse click
                Coordinate center = new Coordinate(arg0.getX(), arg0.getY());
                Circle newCircle = new Circle(radius, center, homescreenController.getClientModelPenColor());
                homescreenController.placeShape(newCircle);                
            }

            //If mouse is moved, we want to update hover shape of canvasPanel
            @Override
            public void mouseMoved(MouseEvent arg0) {
                //Create Coordinate object for location of mouse
                Coordinate center = new Coordinate(arg0.getX(), arg0.getY());
                //Create new circle centered here and update hover of canvasPanel to this circle
                Circle newHoverCircle = new Circle(radius, center, homescreenController.getClientModelPenColor());
                canvasPanel.setHover(newHoverCircle);
                //Set lastMouseLocation
                lastMouseLocation = center;
            }
            
        };
        //return created listener
        return listener;
    }
}
