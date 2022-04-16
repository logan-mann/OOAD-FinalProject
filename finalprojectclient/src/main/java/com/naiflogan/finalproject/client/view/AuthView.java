package com.naiflogan.finalproject.client.view;


import javax.swing.*;

import com.naiflogan.finalproject.client.mediator.AuthMediator;

import java.awt.*;
import java.awt.event.*;

public class AuthView extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private JPanel cards;

    final static String LOGIN_PANEL = "Login Panel";
    final static String SELECTION_PANEL = "Selection Panel";
    final static String CREATE_ACCOUNT_PANEL = "Create Account Panel";

    private boolean usernameModified = false;
    private boolean passwordModified = false;
    private AuthMediator authMediator;

    public AuthView() {
        authMediator = new AuthMediator(this);
        cards = new JPanel(new CardLayout());
        JPanel selectionPanel = selectionPanel();
        JPanel loginPanel = loginPanel();
        JPanel createAccountPanel = new CreateAccountView(authMediator);
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

    private JPanel loginPanel() {
        JPanel loginPanel = new JPanel();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JTextField usernameField = new JTextField("Username", 20);
        usernameField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (usernameModified == false) {
                    usernameField.setText("");
                    usernameModified = true;
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
                if (passwordModified == false) {
                    passwordField.setText("");
                    passwordModified = true;
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
                CardLayout cl = (CardLayout)(cards.getLayout());
                
                cl.previous(cards);
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        centerPanel.add(buttonPanel);
        centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);


        loginPanel.add(centerPanel, BorderLayout.CENTER);
        return loginPanel;
    }

    private JPanel selectionPanel() {
        JPanel selectionPanel = new JPanel();


        JButton createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authMediator.showCreateAccountView();
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

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}
