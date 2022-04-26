package com.naiflogan.finalproject.client.strategy;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.shapes.Coordinate;
import com.naiflogan.finalproject.client.shapes.Rectangle;

/**
 * Implementation of ShapeCreationStrategy for Rectangles, part of STRATEGY PATTERN
 */
public class RectangleCreationStrategy implements ShapeCreationStrategy {

    //State variables that are updated via shape properties menu
    private int length;
    private int height;

    //Last mouse location on canvas
    private Coordinate lastMouseLocation = new Coordinate(0, 0);

    public RectangleCreationStrategy() {
        this.length = 50;
        this.height = 70;
    }

    //This builds and returns a JPanel containing the properties menu for rectangles that will be displayed on main application screen
    @Override
    public JPanel getShapePropertiesMenu(CanvasPanel canvasPanel, HomescreenController homescreenController) {

        //Create JPanel to contain entire menu, set to vertical layout
        JPanel propertiesMenu = new JPanel();
        propertiesMenu.setLayout(new BoxLayout(propertiesMenu, BoxLayout.Y_AXIS));
        
        //Create label for length
        JLabel lengthDisplay = new JLabel("Length: " + length);
        //Create panel to house length controls
        JPanel lengthPanel = new JPanel();
        //Set to vertical layout
        lengthPanel.setLayout(new BoxLayout(lengthPanel, BoxLayout.Y_AXIS));
        //Create buttons for decreasing and increasing length
        JButton decreaseLengthButton = new JButton("-");
        JButton increaseLengthButton = new JButton("+");
        //Add action listener to decreaseLengthButton to decrease length on button press
        decreaseLengthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //If length > 5, decrease length by 5
                if (length > 5) {
                    length-=5;
                    //Create new shape to be drawn over mouse location
                    Rectangle newRectangle = new Rectangle(length, height, lastMouseLocation, homescreenController.getClientModelPenColor());
                    //Set the hover object to this new rectangle
                    canvasPanel.setHover(newRectangle);
                    //Update display to show new length, refresh display
                    lengthDisplay.setText(getLengthLabelText());
                    lengthDisplay.revalidate();
                    lengthDisplay.repaint();
                }
            }
            
        });
        //Add action listener to increaseLengthButton to increase length on button press
        increaseLengthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                length+=5;
                //Create new shape to be drawn over mouse location
                Rectangle newRectangle = new Rectangle(length, height, lastMouseLocation, homescreenController.getClientModelPenColor());
                //Set the hover object of canvasPanel to this new rectangle
                canvasPanel.setHover(newRectangle);
                //Update display to show new length
                lengthDisplay.setText(getLengthLabelText());
                lengthDisplay.revalidate();
                lengthDisplay.repaint();
            }
            
        });
        //Add header to properties menu
        propertiesMenu.add(new JLabel("Current Shape Type: Rectangle"), Component.TOP_ALIGNMENT);
        //Add elements to lengthPanel
        lengthPanel.add(increaseLengthButton);
        lengthPanel.add(lengthDisplay);
        lengthPanel.add(decreaseLengthButton);

//Beginning of height panel 
        
        //Create label for height
        JLabel heightDisplay = new JLabel("Height: " + height);
        //JPanel to house height controls
        JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new BoxLayout(heightPanel, BoxLayout.Y_AXIS));
        //Buttons for increasing and decreasing height
        JButton decreaseHeightButton = new JButton("-");
        JButton increaseHeightButton = new JButton("+");
        //Add event handlers same as length buttons
        decreaseHeightButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (height > 5) {
                    height-=5;
                    Rectangle newRectangle = new Rectangle(length, height, lastMouseLocation, homescreenController.getClientModelPenColor());
                    canvasPanel.setHover(newRectangle);
                    heightDisplay.setText(getHeightLabelText());
                    heightDisplay.revalidate();
                    heightDisplay.repaint();
                }
            }
            
        });
        increaseHeightButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                height+=5;
                Rectangle newRectangle = new Rectangle(length, height, lastMouseLocation, homescreenController.getClientModelPenColor());
                canvasPanel.setHover(newRectangle);
                heightDisplay.setText(getHeightLabelText());
                heightDisplay.revalidate();
                heightDisplay.repaint();
            }
            
        });
        //Add spacing to properties menu panel
        propertiesMenu.add(Box.createVerticalStrut(25));
        //Add child components to heightPanel
        heightPanel.add(increaseHeightButton);
        heightPanel.add(heightDisplay);
        heightPanel.add(decreaseHeightButton);
        //Add length and height panels to parent panel with space between
        propertiesMenu.add(lengthPanel);
        propertiesMenu.add(Box.createVerticalStrut(25));
        propertiesMenu.add(heightPanel);
        return propertiesMenu;
    }

    private String getLengthLabelText() {
        return "Length: " + length;
    }

    private String getHeightLabelText() {
        return "Height: " + height;
    }


    //Implementation to create mouse listener for rectangle shape creation, listener returned here should be added as a listener to canvasPanel
    @Override
    public MouseInputListener getShapeCreationListener(CanvasPanel canvasPanel, HomescreenController homescreenController) {
        MouseInputListener listener = new MouseInputAdapter() {

            //If mouse clicked we want to place a rectangle here
            @Override
            public void mouseClicked(MouseEvent arg0) {
                //Get location of mouse click, build Coordinate object with this info
                Coordinate center = new Coordinate(arg0.getX(), arg0.getY());
                //Create rectangle object centered at click location, with values of height and length
                Rectangle newRectangle = new Rectangle(length, height, center, homescreenController.getClientModelPenColor());
                //Place the shape on canvas via homeController
                homescreenController.placeShape(newRectangle);                
            }

            //If mouse moved, we want to update the location of the "hover" shape on the canvas
            @Override
            public void mouseMoved(MouseEvent arg0) {
                //Get location of mouse
                Coordinate center = new Coordinate(arg0.getX(), arg0.getY());
                //Create rectangle object centered at click location, with values of height and length
                Rectangle newRectangle = new Rectangle(length, height, center, homescreenController.getClientModelPenColor()); 
                //Update hover shape of canvasPanel to this shape, update lastMouseLocation
                canvasPanel.setHover(newRectangle);
                lastMouseLocation = center;

            }
            
        };
        //return the listener we just defined to be used by canvasPanel
        return listener;
    }
}
