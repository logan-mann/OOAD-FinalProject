package com.naiflogan.finalproject.client.view;


import javax.swing.*;

import com.naiflogan.finalproject.client.controller.AuthController;


import java.awt.*;
import java.awt.event.*;

/**
 * AuthView is the Component which houses Login and CreateAccount Views
 * This View does not depend on a model, so we don't inherit from MVC View 
 */
public class AuthView extends JPanel {

    private JPanel cards;

    //Values for different child views to be given to CardLayout
    final static String LOGIN_PANEL = "Login Panel";
    final static String SELECTION_PANEL = "Selection Panel";
    final static String CREATE_ACCOUNT_PANEL = "Create Account Panel";

    public AuthView(AuthController authController) {
        //Set vertical layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Create Login, CreateAccount, and SelectionViews and add them to CardLayout
        cards = new JPanel(new CardLayout());
        JPanel selectionPanel = selectionPanel();
        JPanel loginPanel = new LoginView(this, authController);
        JPanel createAccountPanel = new CreateAccountView(this, authController);
        cards.add(selectionPanel, SELECTION_PANEL);
        cards.add(loginPanel, LOGIN_PANEL);
        cards.add(createAccountPanel, CREATE_ACCOUNT_PANEL);
        //Add a welcome label to auth screen
        this.add(new JLabel("Welcome to CloudCanvas!"), Component.CENTER_ALIGNMENT);
        //Add cards to the AuthView
        this.add(cards, BorderLayout.CENTER);
    }

    //Method to show previous view in stack
    public void showPreviousView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
                
        cl.previous(cards);
    }

    //Show LOGIN_VIEW
    public void showLoginView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
                
        cl.show(cards, LOGIN_PANEL);
    }

    //Show CREATE_ACCOUNT_VIEW
    public void showCreateAccountView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
                
        cl.show(cards, CREATE_ACCOUNT_PANEL);
    }

    //Show SELECTION_VIEW
    public void showSelectionView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
                
        cl.show(cards, SELECTION_PANEL);
    }

    //Helper function to create selection panel
    private JPanel selectionPanel() {
        //Panel to house components
        JPanel selectionPanel = new JPanel();

        //Button to go to create account view
        JButton createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        //Add listener to show createAccountView on click
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateAccountView();
            }
        });

        //Button to go to login view
        JButton loginButton = new JButton();
        loginButton.setText("Login");
        //Add listener to go to login view when clicked
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login Pushed");
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, LOGIN_PANEL);
            }
        });

        
        //Add buttons to panel, return selection panel
        JPanel centerPanel = new JPanel();
        centerPanel.add(loginButton);
        centerPanel.add(createAccountButton);

        selectionPanel.add(centerPanel, BorderLayout.CENTER);
        return selectionPanel;
    }
}
