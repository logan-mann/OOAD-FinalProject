package com.naiflogan.finalproject.client.strategy;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.HomescreenController;

/**
 * ShapeCreationStrategy defines an interface for the functions required for shape creation that differ between different shape types
 * Allows us to keep the logic requried for shape creation in one place, rather than having it scattered throughout code for various views, changing methods when shapeType changes, etc.
 * Implements STRATEGY PATTERN
 */
public interface ShapeCreationStrategy {

    //Returns a properties menu JPanel for the specific 'strategy' of said shape
    public JPanel getShapePropertiesMenu(CanvasPanel canvasPanel, HomescreenController homescreenController);
    //Strategy to create mouse listener that handles shape creation of a given shape type, rendering hover objects on canvas, etc.
    public MouseInputListener getShapeCreationListener(CanvasPanel canvasPanel, HomescreenController homescreenController);
}
