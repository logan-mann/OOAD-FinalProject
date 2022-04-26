package com.naiflogan.finalproject.client.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;
import com.naiflogan.finalproject.client.shapes.ColorConstants;

/***
 * This View displays a menu of color options
 * It displays a row of square buttons which when pushed, change the currentPenColor property of clientModel via HomescreenController
 * Part of MVC PATTERN, implements our MVC View interface
 */
public class PenSelectionView extends JPanel implements View {

    private HomescreenController homescreenController;
    private ClientModel clientModel;
    private Map<ColorConstants.Colors, JPanel> colorButtons;


    public PenSelectionView(HomescreenController homescreenController, ClientModel clientModel) {
        this.homescreenController = homescreenController;
        this.clientModel = clientModel;
        this.clientModel.attach(this);
        this.colorButtons = new HashMap<>();
        this.removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(500,100));
        renderUi();
    }

    public void renderUi() {

        //For each color in ColorConstants enum
        for (ColorConstants.Colors color : ColorConstants.Colors.values()) {

            //Initialize button and colorHexVal
            JPanel colorButton;
            String colorHexVal;

            //If a button has already been created for this color, set vars appropriately
            if (colorButtons.containsKey(color)) {
                colorButton = colorButtons.get(color);
                colorHexVal = ColorConstants.Colors.getColorHexValue(color);
            
            //Else create new button, get hexVal for current color
            } else {
                colorButton = new JPanel();
                colorHexVal = ColorConstants.Colors.getColorHexValue(color);
                colorButton.setPreferredSize(new Dimension(50,50));
                //Add listener to set current pen color when button is pressed
                colorButton.addMouseListener(new MouseInputAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        homescreenController.setCurrentPenColor(colorHexVal);
                    }
                });
                //Set the background color of the button appropriately
                colorButton.setBackground(Color.decode(colorHexVal));
                //Add color button to the main component
                this.add(colorButton);
                //Add spacing between each button
                this.add(Box.createRigidArea(new Dimension(5, 0)));
                //Add current button to map
                this.colorButtons.put(color, colorButton);

            }

            //Set the selected color to look like it is pressed, others to look like not pressed
            if (colorHexVal.equals(clientModel.getCurrentPenColor())) {
                Border titled = BorderFactory.createTitledBorder("Selected");
                Border lowered = BorderFactory.createLoweredBevelBorder();
                colorButton.setBorder(BorderFactory.createCompoundBorder(lowered,titled));
            } else {
                colorButton.setBorder(BorderFactory.createRaisedBevelBorder());

            }

        }
        
    }

    @Override
    public void update() {
        renderUi();
        this.revalidate();
        this.repaint();
        
    }


    
}
