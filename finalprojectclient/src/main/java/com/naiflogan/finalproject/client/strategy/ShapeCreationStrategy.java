package com.naiflogan.finalproject.client.strategy;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.shapes.Coordinate;

public interface ShapeCreationStrategy {

    //Returns a properties menu for the specific 'strategy' of said shape
    public JPanel getShapePropertiesMenu(CanvasPanel canvasPanel);
    //Returns the mouse listener strategy to handle creating shape pertaining to strategy
    public MouseInputListener getShapeCreationListener(CanvasPanel canvasPanel, HomescreenController homescreenController);
}
