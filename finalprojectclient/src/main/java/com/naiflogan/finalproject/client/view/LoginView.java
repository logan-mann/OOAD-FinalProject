package com.naiflogan.finalproject.client.view;

import javax.swing.*;

import com.naiflogan.finalproject.client.mediator.AuthMediator;

import java.awt.*;
import java.awt.event.*;

public class LoginView extends JPanel {

    private boolean usernameFocused;
    private boolean passwordFocused;
    private AuthMediator authMediator;

    public LoginView(AuthMediator mediator){
        authMediator = mediator;

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

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
        centerPanel.add(usernameField);


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
        centerPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


        JButton loginButton = new JButton("Login");
            
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login Pushed");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authMediator.showSelectionView();
            }
        });


        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        centerPanel.add(buttonPanel);
        centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);


        this.add(centerPanel, BorderLayout.CENTER);
    }
    
}


