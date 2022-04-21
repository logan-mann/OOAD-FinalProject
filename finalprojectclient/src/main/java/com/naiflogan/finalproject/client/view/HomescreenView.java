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


    public HomescreenView(ClientModel clientModel, HomescreenController homescreenController) {
        this.clientModel = clientModel;
        clientModel.attach(this);
        this.homescreenController = homescreenController;
        this.penSelectionView = new PenSelectionView(homescreenController, clientModel);
        render();
    }


    public void render() {
        this.removeAll();
        JPanel holder = new JPanel();
        holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));
        if (clientModel.getUser() != null) {
            holder.add(new JLabel("Welcome, " + clientModel.getUser().getUsername() + "!"));
        }
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        if (clientModel.getCurrentCanvas() != null) {
            JPanel canvasHolder = new JPanel();
            if (canvasPanel == null) {
                canvasPanel = new CanvasPanel(clientModel.getCurrentCanvas().getShapes(), clientModel);
                MouseInputListener listener = clientModel.getShapeCreationStrategy().getShapeCreationListener(canvasPanel, homescreenController);
                canvasPanel.setMouseListener(listener);
                clientModel.attach(canvasPanel);
            } else {
                canvasPanel.setShapes(clientModel.getCurrentCanvas().getShapes());
            }
            canvasHolder.add(canvasPanel);
            centerPanel.add(clientModel.getShapeCreationStrategy().getShapePropertiesMenu(canvasPanel, homescreenController));
            centerPanel.add(canvasHolder);
            SelectCanvasView selectCanvasView = new SelectCanvasView(clientModel.getCanvases(), homescreenController, clientModel);
            selectCanvasView.setAutoscrolls(true);
            JScrollPane scrollPane = new JScrollPane(selectCanvasView);
            scrollPane.setPreferredSize(new Dimension(200,500));
            scrollPane.getVerticalScrollBar().setUnitIncrement(8);
            JPanel scrollHolder = new JPanel();
            scrollHolder.add(scrollPane);
            centerPanel.add(scrollHolder);

        }
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(this.penSelectionView);
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
