package com.naiflogan.finalproject.client.view;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;

import com.naiflogan.finalproject.client.Utils;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.shapes.ShapeType;

/**
 * This class implements a UI component which displays the possible shape types, buttons for user to click to choose shape type they wish to place
 * Part of MVC PATTERN, implements our MVC View interface
 * References state contained in ClientModel
 */
public class ShapeSelectionView extends JPanel implements View {

    //Model object this component references
    private ClientModel clientModel;
    //Map of buttons for shape selection, maps shape type to button object
    private Map<ShapeType, JPanel> shapeButtons;
    //Homescreen controller for modifying model state
    private HomescreenController homescreenController;
    
    public ShapeSelectionView(ClientModel clientModel, HomescreenController homescreenController) {
        this.clientModel = clientModel;
        this.homescreenController = homescreenController;
        this.shapeButtons = new HashMap<>();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        renderUi();
    }

    //Helper function to render UI
    private void renderUi() {
        //Iterate over all shape types
        for (ShapeType shapeType : ShapeType.values()) {
            //Button object
            JPanel shapeButton;
            //If component doesn't already contain a button pertaining to this shape type, need to create one and add it to the view
            if (!shapeButtons.containsKey(shapeType)) {
                //Create button
                shapeButton = new JPanel();
                //Label text = ShapeType name
                String labelText = Utils.capitalizeFirstLetter(shapeType.name());
                //Add the label to button
                shapeButton.add(new JLabel(labelText), Component.CENTER_ALIGNMENT);
                //Add shape button to main component
                this.add(shapeButton);
                //Add button to button map
                shapeButtons.put(shapeType, shapeButton);
                //Add a listener to button to set current shape type when clicked
                shapeButton.addMouseListener(new MouseInputAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        homescreenController.setCurrentShapeType(shapeType);
                    }
                });
            //If button has already been created, just retrieve it from buttons map
            } else {
                shapeButton = shapeButtons.get(shapeType);
            }
            //If the button pertains to the current shape type, make it have a lowered border
            //Makes it look like the button is selected
            if (shapeType == clientModel.getCurrentShapeType()) {
                shapeButton.setBorder(BorderFactory.createLoweredBevelBorder());
            //Otherwise make the border raised
            } else {
                shapeButton.setBorder(BorderFactory.createRaisedBevelBorder());
            }
        }
    }

    public void paintComponent(Graphics g) {
        renderUi();
    }

    @Override
    public void update() {
        revalidate();
        repaint();
        
    }
    
}
