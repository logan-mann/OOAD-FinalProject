package com.naiflogan.finalproject.client.view;

import javax.swing.*;

import com.naiflogan.finalproject.client.controller.AuthController;

import java.awt.*;
import java.awt.event.*;

/**
 * This class implements the Login UI View
 * This view does not depend on any model, it's just a stateless form, so we dont use our MVC View interface here
 */
public class LoginView extends JPanel {

    //Form state variables
    private boolean usernameFocused;
    private boolean passwordFocused;
    //Parent view
    private AuthView parent;

    //Controller for submitting login actions
    private AuthController authController;

    public LoginView(AuthView parent, AuthController authController){
        this.parent = parent;
        this.authController = authController;
        renderUi();
    }
    

    //Helper method to render UI
    private void renderUi() {
        //Remove all child components
        this.removeAll();
        //Panel to house child components, vertical layout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //Create field for username, add listener to clear placeholder text when clicked
        JTextField usernameField = new JTextField("Username", 20);
        usernameField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (usernameFocused == false) {
                    usernameField.setText("");
                    usernameFocused = true;
                };                
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        //Add view to main panel
        centerPanel.add(usernameField);

        //Password text field, add listener to clear placeholder when first clicked
        JPasswordField passwordField = new JPasswordField("Password", 20);
        passwordField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (passwordFocused == false) {
                    passwordField.setText("");
                    passwordFocused = true;
                };                
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        //Add password field to main panel
        centerPanel.add(passwordField);

        //Create panel to house Back and Login Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        //Login button
        JButton loginButton = new JButton("Login");
        //Add listener to submit login action when button pressed
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authController.login(usernameField.getText(), new String(passwordField.getPassword()));
                usernameField.setText("Username");
                passwordField.setText("Password");
                usernameFocused = false;
                passwordFocused = false;
            }
        });
        //Back button
        JButton backButton = new JButton("Back");
        //Listener to return to parent's selection view when clicked
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset form values
                usernameField.setText("Username");
                passwordField.setText("Password");
                usernameFocused = false;
                passwordFocused = false;

                parent.showSelectionView();
            }
        });


        //Add items to button panel, button panel to main view
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        centerPanel.add(buttonPanel);
        centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);


        this.add(centerPanel, BorderLayout.CENTER);

    }
}


