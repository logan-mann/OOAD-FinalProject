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

public class LineCreationStrategy implements ShapeCreationStrategy {

    private Coordinate firstClickLocation;
    private Coordinate secondClickLocation;

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

    @Override
    public MouseInputListener getShapeCreationListener(CanvasPanel canvasPanel,
            HomescreenController homescreenController) {
                return new MouseInputAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        Coordinate center = new Coordinate(arg0.getX(), arg0.getY());

                        if (firstClickLocation == null) {
                            firstClickLocation = center;
                        } else {
                            secondClickLocation = center;
                            Line newLine = new Line(firstClickLocation, secondClickLocation, homescreenController.getClientModelPenColor());
                            firstClickLocation = null;
                            secondClickLocation = null;
                            homescreenController.placeShape(newLine);
                        }            
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        firstClickLocation = null;
                        secondClickLocation = null;
                        canvasPanel.setHover(new Circle(0, new Coordinate(0,0), homescreenController.getClientModelPenColor()));
                    }

                    @Override
                    public void mouseMoved(MouseEvent arg0) {
                        Coordinate center = new Coordinate(arg0.getX(), arg0.getY());

                        if (firstClickLocation == null) {
                            Circle newHoverCircle = new Circle(3, center, homescreenController.getClientModelPenColor());
                            canvasPanel.setHover(newHoverCircle);
                        } else {
                            Line newHoverLine = new Line(firstClickLocation, center, homescreenController.getClientModelPenColor());
                            canvasPanel.setHover(newHoverLine);
                        }
                    }
                };
    }
    
}
