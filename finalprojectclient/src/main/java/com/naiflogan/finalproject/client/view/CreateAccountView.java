package com.naiflogan.finalproject.client.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.naiflogan.finalproject.client.controller.AuthController;
import com.naiflogan.finalproject.client.mediator.AuthMediator;

import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.event.*;

public class CreateAccountView extends JPanel {
    private boolean usernameFocused = false;
    private boolean passwordFocused = false;
    private boolean confirmPasswordFocused = false;

    private String username = "username";
    private String password = "password";
    private String confirmPassword = "confirmPassword";

    private AuthMediator authMediator;

    private AuthController authController;

    public CreateAccountView(AuthMediator mediator, AuthController authController){
        authMediator = mediator;
        this.authController = authController;

        //Create panel to house everything and set layout to vertical layout (stack elements)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


        //Create Account Button
        JButton createAccountButton = new JButton("Create Account");

        //Username field
        JTextField usernameField = new JTextField("Username", 20);
        //Add listener to clear placeholder text when field is first clicked
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

        //Add listener to update username variable and enable button if form entries valid
        usernameField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                username = usernameField.getText();
                if (usernameFocused && !username.equals("") && confirmPassword.equals(password) && !confirmPassword.equals("")) {
                    createAccountButton.setEnabled(true);
                } else {
                    createAccountButton.setEnabled(false);
                };
                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                changedUpdate(e);
            }
            
        });
        //Add username field to main panel
        centerPanel.add(usernameField);


        //Password field
        JPasswordField passwordField = new JPasswordField("Password", 20);
        //Add listener to clear field when first clicked
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

        //Confirm password field
        JPasswordField confirmPasswordField = new JPasswordField("Password", 20);
        //Add focus listener to clear field when first clicked
        confirmPasswordField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (confirmPasswordFocused == false) {
                    confirmPasswordField.setText("");
                    confirmPasswordFocused = true;
                };                
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        //Add listener to update form state and enable create account button if entries are valid
        confirmPasswordField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                confirmPassword = new String(confirmPasswordField.getPassword());
                if (password.equals(confirmPassword) && !password.equals("") && usernameFocused && !username.equals("")) {
                    createAccountButton.setEnabled(true);
                } else {
                    createAccountButton.setEnabled(false);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }
            
        });
        //Add listener to update form state and enable create account button if entries are valid
        passwordField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("RAN");
                password = new String(passwordField.getPassword());
                if (password.equals(confirmPassword) && !password.equals("") && usernameFocused && !username.equals("")) {
                    createAccountButton.setEnabled(true);
                } else {
                    createAccountButton.setEnabled(false);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }
            
        });
        //Add confirm password field to main panel
        centerPanel.add(confirmPasswordField);

        //Create panel to house Back and create account buttons
        JPanel buttonPanel = new JPanel();
        //Set horizontal layout for button panel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
            
        //Add action listener for when CreateAccountButton is pushed
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                authController.createAccount(username, password);
            }
        });

        //Add action listner for when back button is pushed
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authMediator.showSelectionView();
            }
        });

        //Initially disable createAccountButton
        createAccountButton.setEnabled(false);

        //Add buttons to button panel
        buttonPanel.add(createAccountButton);
        buttonPanel.add(backButton);

        //Add button panel to main view
        centerPanel.add(buttonPanel);
        //Center java.awt.Components vertically
        centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        //Add main view to encapsulating view
        this.add(centerPanel, BorderLayout.CENTER);
    }
    
}
