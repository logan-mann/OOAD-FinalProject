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

public class ShapeSelectionView extends JPanel implements View {

    private ClientModel clientModel;
    private Map<ShapeType, JPanel> shapeButtons;
    private HomescreenController homescreenController;
    
    public ShapeSelectionView(ClientModel clientModel, HomescreenController homescreenController) {
        this.clientModel = clientModel;
        this.homescreenController = homescreenController;
        this.shapeButtons = new HashMap<>();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        renderUi();
    }

    public void renderUi() {
        for (ShapeType shapeType : ShapeType.values()) {
            JPanel shapeButton;
            if (!shapeButtons.containsKey(shapeType)) {
                shapeButton = new JPanel();
                String labelText = Utils.capitalizeFirstLetter(shapeType.name());
                shapeButton.add(new JLabel(labelText), Component.CENTER_ALIGNMENT);
                this.add(shapeButton);
                shapeButtons.put(shapeType, shapeButton);
                shapeButton.addMouseListener(new MouseInputAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        homescreenController.setCurrentShapeType(shapeType);
                    }
                });
            } else {
                shapeButton = shapeButtons.get(shapeType);
            }
            if (shapeType == clientModel.getCurrentShapeType()) {
                shapeButton.setBorder(BorderFactory.createLoweredBevelBorder());
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
