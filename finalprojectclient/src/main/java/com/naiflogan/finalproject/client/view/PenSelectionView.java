package com.naiflogan.finalproject.client.view;

import javax.swing.*;
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
        for (ColorConstants.Colors color : ColorConstants.Colors.values()) {
            JPanel colorButton;
            String colorHexVal;

            if (colorButtons.containsKey(color)) {
                colorButton = colorButtons.get(color);
                colorHexVal = ColorConstants.Colors.getColorHexValue(color);
            } else {
                colorButton = new JPanel();
                colorHexVal = ColorConstants.Colors.getColorHexValue(color);
                colorButton.setPreferredSize(new Dimension(50,50));
                colorButton.addMouseListener(new MouseInputAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        homescreenController.setCurrentPenColor(colorHexVal);
                    }
                });
                colorButton.setBackground(Color.decode(colorHexVal));
                this.add(colorButton);
                this.add(Box.createRigidArea(new Dimension(5, 0)));
                this.colorButtons.put(color, colorButton);

            }

            if (colorHexVal.equals(clientModel.getCurrentPenColor())) {
                colorButton.setBorder(BorderFactory.createLoweredBevelBorder());
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
