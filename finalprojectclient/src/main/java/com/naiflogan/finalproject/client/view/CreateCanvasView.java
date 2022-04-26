package com.naiflogan.finalproject.client.view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import com.naiflogan.finalproject.client.controller.HomescreenController;

/**
 * This class implements the CreateCanvas UI View
 * This is part of the MVC PATTERN, and inherits from View
 */
public class CreateCanvasView extends JPanel implements View {

    //HomescreenController for sending requests/updating model
    private HomescreenController homescreenController;

    //State variables for Canvas Creation form fields
    private boolean canvasNameFocused = false;
    private boolean isPublic = true;

    public CreateCanvasView(HomescreenController homescreenController) {
        this.homescreenController = homescreenController;
        renderUi();
    }

    //Helper function for rendering UI
    private void renderUi() {
        //Remove all child components
        this.removeAll();
        //Create panel to house inner components, vertical layout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //Create text field for user to enter new canvas' name
        JTextField canvasNameField = new JTextField("Canvas Name", 20);
        //Add a focus listener to clear placeholder when field clicked for first time
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
        //Add field to parent panel
        centerPanel.add(canvasNameField, Component.CENTER_ALIGNMENT);

        //Create radio button for choosing whether new canvas is public or not
        JRadioButton isPublicButton = new JRadioButton("Is Public", isPublic);
        centerPanel.add(isPublicButton, Component.LEFT_ALIGNMENT);
        //Add listener to update state variable when form field changed
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

        //Create panel to house back and create canvas buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        //Create canvas button
        JButton createButton = new JButton("Create Canvas");
            
        //Add listener to create canvas when button pressed
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Create new canvas via homescreen controller
                homescreenController.createNewCanvas(canvasNameField.getText(), isPublic);
            }
        });

        //Back button
        JButton backButton = new JButton("Back");
        //Add listener to go back to main application view when button pressed
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homescreenController.goToHomescreenView();
            }
        });

        //Add buttons to button panel and add button panel to center panel
        buttonPanel.add(createButton);
        buttonPanel.add(backButton);

        centerPanel.add(buttonPanel);
        centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        //Add center panel to the main component
        this.add(centerPanel, BorderLayout.CENTER);

    }

    //Don't need to do anything on update
    @Override
    public void update() {

    }
    
}
