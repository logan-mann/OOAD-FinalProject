package com.naiflogan.finalproject.client.strategy;

import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
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

    private Coordinate firstClick;
    private Coordinate secondClick;

    @Override
    public JPanel getShapePropertiesMenu(CanvasPanel canvasPanel, HomescreenController homescreenController) {
        JPanel linePanel = new JPanel();
        linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.Y_AXIS));
            
        JTextArea instructions = new JTextArea("Line Placement Instructions:\n\n" + 
        "Click to place line starting point at the mouse cursor.\n\n" +
        "Click again place line end point at the mouse cursor and place the line on the canvas.\n\n"+
        "Move the cursor off of the canvas to reset the line.\n");
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

                        if (firstClick == null) {
                            firstClick = center;
                        } else {
                            secondClick = center;
                            Line newLine = new Line(firstClick, secondClick, homescreenController.getClientModelPenColor());
                            firstClick = null;
                            secondClick = null;
                            homescreenController.placeShape(newLine);
                        }            
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        firstClick = null;
                        secondClick = null;
                        canvasPanel.setHover(new Circle(0, new Coordinate(0,0), homescreenController.getClientModelPenColor()));
                    }

                    @Override
                    public void mouseMoved(MouseEvent arg0) {
                        Coordinate center = new Coordinate(arg0.getX(), arg0.getY());

                        if (firstClick == null) {
                            Circle newHoverCircle = new Circle(3, center, homescreenController.getClientModelPenColor());
                            canvasPanel.setHover(newHoverCircle);
                        } else {
                            Line newHoverLine = new Line(firstClick, center, homescreenController.getClientModelPenColor());
                            canvasPanel.setHover(newHoverLine);
                        }
                    }
                };
    }
    
}
