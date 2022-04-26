package com.naiflogan.finalproject.client.strategy;

import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;

import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.shapes.Circle;
import com.naiflogan.finalproject.client.shapes.Coordinate;
import com.naiflogan.finalproject.client.shapes.Line;

/**
 * Implementation of ShapeCreationStrategy for Lines, part of STRATEGY PATTERN
 */
public class LineCreationStrategy implements ShapeCreationStrategy {

    //Coordinate of two most recent clicks on canvas
    private Coordinate firstClickLocation;
    private Coordinate secondClickLocation;

    //This builds and returns a JPanel containing the properties menu for lines that will be displayed on main application screen
    //Lines don't have properties to set, so we essentially return a text field w/ instructions on how line placement works
    @Override
    public JPanel getShapePropertiesMenu(CanvasPanel canvasPanel, HomescreenController homescreenController) {
        JPanel linePanel = new JPanel();
        linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.Y_AXIS));
            
        JTextArea instructions = new JTextArea("Line Placement Instructions:\n" + 
        "Click to place line starting \npoint at the mouse cursor.\n\n" +
        "Click again place line end \npoint at the mouse cursor \nand place the line \non the canvas.\n\n"+
        "Move the cursor off of the \ncanvas to reset the line.\n");
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setOpaque(false);
        linePanel.setPreferredSize(new Dimension(180, 500));
        linePanel.add(instructions);
        return linePanel;
    }

    //Implementation to create mouse listener for line creation, listener returned here should be added as a listener to canvasPanel
    //Takes in homescreenController that should be used to place shapes, and canvasPanel that will be adding return value as a listener
    @Override
    public MouseInputListener getShapeCreationListener(CanvasPanel canvasPanel,
            HomescreenController homescreenController) {
                //Create and return new mouse adapter to be added to canvasPanel
                return new MouseInputAdapter() {

                    //When mouse clicked, we want to set firstClickLocation and secondClickLocation appropriately
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        //Build Coordinate object with location of new click
                        Coordinate center = new Coordinate(arg0.getX(), arg0.getY());

                        //If firstClickLocation hasn't been set, we simply set it to new click location
                        if (firstClickLocation == null) {
                            firstClickLocation = center;
                        //If the user has already clicked once, we want to set secondClickLocation and place a line starting at firstClickLocation, ending at secondClickLocation
                        } else {
                            //set secondClickLocation appropriately
                            secondClickLocation = center;
                            //Create a new line object starting at firstClick, ending at secondClick, w/ current color of homescreenController's clientModel
                            Line newLine = new Line(firstClickLocation, secondClickLocation, homescreenController.getClientModelPenColor());
                            //Once line is created, reset click locations for next line
                            firstClickLocation = null;
                            secondClickLocation = null;
                            //Place the new line on canvas via homescreenController
                            homescreenController.placeShape(newLine);
                        }            
                    }

                    //If mouse leaves the canvas panel, we want to reset click locations
                    @Override
                    public void mouseExited(MouseEvent e) {
                        firstClickLocation = null;
                        secondClickLocation = null;
                        //If nothing has been clicked, just draw a small circle around the mouse pointer
                        canvasPanel.setHover(new Circle(0, new Coordinate(0,0), homescreenController.getClientModelPenColor()));
                    }

                    //If the mouse is moved, we want to update canvasPanel's hover shape w/ new line stretching from firstClick to new mouse position
                    @Override
                    public void mouseMoved(MouseEvent arg0) {
                        //Get new mouse position
                        Coordinate center = new Coordinate(arg0.getX(), arg0.getY());

                        //If mouse hasn't been clicked once already, we just set hover shape to a small circle centered at mouse location
                        if (firstClickLocation == null) {
                            Circle newHoverCircle = new Circle(3, center, homescreenController.getClientModelPenColor());
                            canvasPanel.setHover(newHoverCircle);
                        //If mouse has been clicked once already, we set canvasPanel's hover shape to a line stretching from firstClick to current mouse position
                        } else {
                            Line newHoverLine = new Line(firstClickLocation, center, homescreenController.getClientModelPenColor());
                            canvasPanel.setHover(newHoverLine);
                        }
                    }
                };
    }
    
}
