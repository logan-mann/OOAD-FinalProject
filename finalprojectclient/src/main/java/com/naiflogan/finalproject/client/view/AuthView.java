package com.naiflogan.finalproject.client.view;


import javax.swing.*;

import com.naiflogan.finalproject.client.controller.AuthController;


import java.awt.*;
import java.awt.event.*;

public class AuthView extends JPanel {

    private JPanel cards;

    final static String LOGIN_PANEL = "Login Panel";
    final static String SELECTION_PANEL = "Selection Panel";
    final static String CREATE_ACCOUNT_PANEL = "Create Account Panel";

    public AuthView(AuthController authController) {
        cards = new JPanel(new CardLayout());
        JPanel selectionPanel = selectionPanel();
        JPanel loginPanel = new LoginView(this, authController);
        JPanel createAccountPanel = new CreateAccountView(this, authController);
        cards.add(selectionPanel, SELECTION_PANEL);
        cards.add(loginPanel, LOGIN_PANEL);
        cards.add(createAccountPanel, CREATE_ACCOUNT_PANEL);
        this.add(cards, BorderLayout.CENTER);
    }

    public void showPreviousView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
                
        cl.previous(cards);
    }

    public void showLoginView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
                
        cl.show(cards, LOGIN_PANEL);
    }

    public void showCreateAccountView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
                
        cl.show(cards, CREATE_ACCOUNT_PANEL);
    }

    public void showSelectionView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
                
        cl.show(cards, SELECTION_PANEL);
    }

    private JPanel selectionPanel() {
        JPanel selectionPanel = new JPanel();


        JButton createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateAccountView();
            }
        });

        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login Pushed");
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, LOGIN_PANEL);
            }
        });

        

        JPanel centerPanel = new JPanel();
        centerPanel.add(loginButton);
        centerPanel.add(createAccountButton);

        selectionPanel.add(centerPanel, BorderLayout.CENTER);
        return selectionPanel;
    }

    public JPanel getCards() {
        return this.cards;
    }


}
