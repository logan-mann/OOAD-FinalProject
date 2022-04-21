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
        this.removeAll();
        JPanel holder = new JPanel();
        holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));
        if (clientModel.getUser() != null) {
            JPanel topPanel = new JPanel();
            topPanel.add(new JLabel("Welcome to CloudCanvas, " + clientModel.getUser().getUsername() + "!"), Component.CENTER_ALIGNMENT);
            holder.add(topPanel);
        }
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        if (clientModel.getCurrentCanvas() != null) {
            JPanel canvasHolder = new JPanel();
            if (canvasPanel == null) {
                canvasPanel = new CanvasPanel(clientModel.getCurrentCanvas().getShapes(), clientModel);
                clientModel.attach(canvasPanel);
            } else {
                canvasPanel.setShapes(clientModel.getCurrentCanvas().getShapes());
            }
            MouseInputListener listener = clientModel.getShapeCreationStrategy().getShapeCreationListener(canvasPanel, homescreenController);
            canvasPanel.setMouseListener(listener);
            canvasHolder.add(canvasPanel);
            JPanel shapePropertiesHolder = new JPanel();
            shapePropertiesHolder.add(clientModel.getShapeCreationStrategy().getShapePropertiesMenu(canvasPanel, homescreenController));
            shapePropertiesHolder.setPreferredSize(new Dimension(200,500));
            shapePropertiesHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            centerPanel.add(shapePropertiesHolder);
            centerPanel.add(canvasHolder);
            centerPanel.add(selectCanvasView);

        }
        selectCanvasView.setCanvases(clientModel.getCanvases());
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(this.penSelectionView);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(this.shapeSelectionView);
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
