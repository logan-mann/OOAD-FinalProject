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

public class RectangleCreationStrategy implements ShapeCreationStrategy {

    private int length;
    private int height;

    private Coordinate lastMouseLocation = new Coordinate(0, 0);

    public RectangleCreationStrategy() {
        this.length = 50;
        this.height = 70;
    }

    @Override
    public JPanel getShapePropertiesMenu(CanvasPanel canvasPanel, HomescreenController homescreenController) {


        JPanel propertiesMenu = new JPanel();
        propertiesMenu.setLayout(new BoxLayout(propertiesMenu, BoxLayout.Y_AXIS));
        //propertiesMenu.setPreferredSize(new Dimension(180,500));
        
        JLabel lengthDisplay = new JLabel("Length: " + length);
        JPanel lengthPanel = new JPanel();
        lengthPanel.setLayout(new BoxLayout(lengthPanel, BoxLayout.Y_AXIS));
        JButton decreaseLengthButton = new JButton("Decrement Length");
        JButton increaseLengthButton = new JButton("Increment Length");
        decreaseLengthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (length > 5) {
                    length-=5;
                    Rectangle newRectangle = new Rectangle(length, height, lastMouseLocation, homescreenController.getClientModelPenColor());
                    canvasPanel.setHover(newRectangle);
                    lengthDisplay.setText(getLengthLabelText());
                    lengthDisplay.revalidate();
                    lengthDisplay.repaint();
                }
            }
            
        });
        increaseLengthButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                length+=5;
                Rectangle newRectangle = new Rectangle(length, height, lastMouseLocation, homescreenController.getClientModelPenColor());
                canvasPanel.setHover(newRectangle);
                lengthDisplay.setText(getLengthLabelText());
                lengthDisplay.revalidate();
                lengthDisplay.repaint();
            }
            
        });
        propertiesMenu.add(new JLabel("Current Shape Type: Rectangle"), Component.TOP_ALIGNMENT);
        lengthPanel.add(increaseLengthButton);
        lengthPanel.add(lengthDisplay);
        lengthPanel.add(decreaseLengthButton);

//Beginning of height panel 

        JLabel heightDisplay = new JLabel("Height: " + height);
        JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new BoxLayout(heightPanel, BoxLayout.Y_AXIS));
        JButton decreaseHeightButton = new JButton("Decrement Height");
        JButton increaseHeightButton = new JButton("Increment Height");
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
        heightPanel.add(increaseHeightButton);
        heightPanel.add(heightDisplay);
        heightPanel.add(decreaseHeightButton);

        propertiesMenu.add(lengthPanel);
        propertiesMenu.add(heightPanel);
        return propertiesMenu;
    }

    private String getLengthLabelText() {
        return "Length: " + length;
    }

    private String getHeightLabelText() {
        return "Height: " + height;
    }

    @Override
    public MouseInputListener getShapeCreationListener(CanvasPanel canvasPanel, HomescreenController homescreenController) {
        MouseInputListener listener = new MouseInputAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                Coordinate center = new Coordinate(arg0.getX(), arg0.getY());
                Rectangle newRectangle = new Rectangle(length, height, center, homescreenController.getClientModelPenColor());
                homescreenController.placeShape(newRectangle);                
            }

            @Override
            public void mouseMoved(MouseEvent arg0) {
                Coordinate center = new Coordinate(arg0.getX(), arg0.getY());
                Rectangle newRectangle = new Rectangle(length, height, center, homescreenController.getClientModelPenColor()); 
                canvasPanel.setHover(newRectangle);
                lastMouseLocation = center;

            }
            
        };
        return listener;
    }
}
