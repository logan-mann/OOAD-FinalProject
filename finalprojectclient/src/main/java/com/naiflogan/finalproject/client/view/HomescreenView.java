package com.naiflogan.finalproject.client.view;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;

import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;

public class HomescreenView extends JPanel implements View {

    private ClientModel clientModel;
    private HomescreenController homescreenController;
    private CanvasPanel canvasPanel;
    private PenSelectionView penSelectionView;
    private ShapeSelectionView shapeSelectionView;
    private SelectCanvasView selectCanvasView;


    public HomescreenView(ClientModel clientModel, HomescreenController homescreenController) {
        this.clientModel = clientModel;
        clientModel.attach(this);
        this.homescreenController = homescreenController;
        this.penSelectionView = new PenSelectionView(homescreenController, clientModel);
        this.shapeSelectionView = new ShapeSelectionView(clientModel, homescreenController);
        this.selectCanvasView = new SelectCanvasView(clientModel.getCanvases(), homescreenController, clientModel);
        render();
    }


    public void render() {
        //Clear component
        this.removeAll();

        //Panel to encapsulate components
        JPanel holder = new JPanel();
        holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));

        //If the user is logged in, add a welcome message to top
        if (clientModel.getUser() != null) {
            JPanel topPanel = new JPanel();
            topPanel.add(new JLabel("Welcome to CloudCanvas, " + clientModel.getUser().getUsername() + "!"), Component.CENTER_ALIGNMENT);
            holder.add(topPanel);
        }

        //Center panel to house Shape Properties Menu, Main Canvas, and Canvas Selection View
        JPanel centerPanel = new JPanel();
        //Horizontal Layout
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        //If the current canvas is not null, we need to add main canvas
        if (clientModel.getCurrentCanvas() != null) {
            //Create holder for the main CanvasPanel
            JPanel canvasHolder = new JPanel();

            //Either initialize canvas panel or update its shapes
            if (this.canvasPanel == null) {
                this.canvasPanel = new CanvasPanel(clientModel.getCurrentCanvas().getShapes(), clientModel);
                this.clientModel.attach(canvasPanel);
            } else {
                //Set the canvasPanel's shapes to the current shapes
                canvasPanel.setShapes(clientModel.getCurrentCanvas().getShapes());
            }
            //add to canvasHolder
            canvasHolder.add(canvasPanel);

            //Set current mouse listener for canvasPanel pertaining to current shape creation strategy
            MouseInputListener listener = clientModel.getShapeCreationStrategy().getShapeCreationListener(canvasPanel, homescreenController);
            canvasPanel.setMouseListener(listener);
            
            //Shape Properties Menu
            JPanel shapePropertiesHolder = new JPanel();
            //Get current shape properties menu from current shapeCreationStrategy
            shapePropertiesHolder.add(clientModel.getShapeCreationStrategy().getShapePropertiesMenu(canvasPanel, homescreenController));
            shapePropertiesHolder.setPreferredSize(new Dimension(200,500));
            shapePropertiesHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            //Add to center panel
            centerPanel.add(shapePropertiesHolder);
            centerPanel.add(canvasHolder);
            centerPanel.add(selectCanvasView);
        }

        //Set canvas list for Select Canvas View
        selectCanvasView.setCanvasList(clientModel.getCanvases());

        //Create panel for Shape Selection and Pen Color Selection
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(this.penSelectionView);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(this.shapeSelectionView);

        //Add encapsulating view to holder
        holder.add(centerPanel);
        holder.add(bottomPanel);
        this.add(holder);
    }

    @Override
    public void update() {
        render();
        this.revalidate();
        this.repaint();
    }
    
}
