package com.naiflogan.finalproject.client.view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;

public class CreateCanvasView extends JPanel implements View {

    private HomescreenController homescreenController;

    private boolean canvasNameFocused = false;
    private boolean isPublic = true;

    public CreateCanvasView(HomescreenController homescreenController) {
        this.homescreenController = homescreenController;
        renderUi();
    }

    public void renderUi() {
        this.removeAll();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JTextField canvasNameField = new JTextField("canvasName", 20);
        canvasNameField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (canvasNameFocused == false) {
                    canvasNameField.setText("");
                    canvasNameFocused = true;
                };                
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        centerPanel.add(canvasNameField, Component.CENTER_ALIGNMENT);


        JRadioButton isPublicButton = new JRadioButton("Is Public", isPublic);
        centerPanel.add(isPublicButton, Component.LEFT_ALIGNMENT);
        isPublicButton.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    isPublic = true;
                }
                else {
                    isPublic = false;
                }
                
            }
            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


        JButton createButton = new JButton("Create Canvas");
            
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("HERE HERE HERE");
                homescreenController.createNewCanvas(canvasNameField.getText(), isPublic);
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homescreenController.goToHomescreenView();
            }
        });


        buttonPanel.add(createButton);
        buttonPanel.add(backButton);

        centerPanel.add(buttonPanel);
        centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);


        this.add(centerPanel, BorderLayout.CENTER);

    }

    @Override
    public void update() {
        
    }
    
}
