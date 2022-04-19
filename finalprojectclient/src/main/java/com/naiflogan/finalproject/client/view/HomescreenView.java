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


    public HomescreenView(ClientModel clientModel, HomescreenController homescreenController) {
        this.clientModel = clientModel;
        clientModel.attach(this);
        this.homescreenController = homescreenController;
        render();
    }


    public void render() {
        this.removeAll();
        JPanel holder = new JPanel();
        holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));
        if (clientModel.getUser() != null) {
            holder.add(new JLabel("Welcome, " + clientModel.getUser().getUsername() + "!"));
        }
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        if (clientModel.getCurrentCanvas() != null) {
            JPanel canvasHolder = new JPanel();
            if (canvasPanel == null) {
                canvasPanel = new CanvasPanel(clientModel.getCurrentCanvas().getShapes());
                MouseInputListener listener = clientModel.getShapeCreationStrategy().getShapeCreationListener(canvasPanel, homescreenController);
                canvasPanel.addMouseListener(listener);
                canvasPanel.addMouseMotionListener(listener);
            } else {
                canvasPanel.setShapes(clientModel.getCurrentCanvas().getShapes());
            }
            canvasHolder.add(canvasPanel);
            bottomPanel.add(clientModel.getShapeCreationStrategy().getShapePropertiesMenu(canvasPanel));
            bottomPanel.add(canvasHolder);
            SelectCanvasView selectCanvasView = new SelectCanvasView(clientModel.getCanvases(), homescreenController);
            selectCanvasView.setAutoscrolls(true);
            JScrollPane scrollPane = new JScrollPane(selectCanvasView);
            scrollPane.setPreferredSize(new Dimension(200,500));
            scrollPane.getVerticalScrollBar().setUnitIncrement(8);
            JPanel scrollHolder = new JPanel();
            scrollHolder.add(scrollPane);
            bottomPanel.add(scrollHolder);
        }
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
