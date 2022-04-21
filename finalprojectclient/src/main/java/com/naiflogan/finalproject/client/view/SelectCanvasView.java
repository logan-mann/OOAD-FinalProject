package com.naiflogan.finalproject.client.view;

import java.awt.*;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;

public class SelectCanvasView extends JPanel implements View {

    private HomescreenController homescreenController;
    private ClientModel clientModel;
    private JPanel canvasList;

    public SelectCanvasView(Map<String,Canvas> canvases, HomescreenController homescreenController, ClientModel clientModel) {
        this.homescreenController = homescreenController;
        this.clientModel = clientModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        canvasList = new JPanel();
        canvasList.setLayout(new BoxLayout(canvasList, BoxLayout.Y_AXIS));
        this.add(canvasList);
        canvasList.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(canvasList);
        scrollPane.setPreferredSize(new Dimension(200,500));
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        JPanel scrollHolder = new JPanel();
        scrollHolder.add(scrollPane);
        this.add(scrollHolder);

        JButton gotoCreateCanvasButton = new JButton("Create New Canvas");
        gotoCreateCanvasButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                homescreenController.goToCreateCanvasView();
            }
            
        });
        this.add(gotoCreateCanvasButton);
        setCanvases(canvases);
    }

    public void setCanvases(Map<String, Canvas> canvases) {
        canvasList.removeAll();
        for (Entry<String, Canvas> entry : canvases.entrySet()) {
            Canvas canvas = entry.getValue();
            canvasList.add(getCanvasListItem(canvas));
        }
    }


    //Helper function to build list item for canvas
    private JPanel getCanvasListItem(Canvas canvas) {
        JPanel canvasListItem = new JPanel();
        canvasListItem.setLayout(new BoxLayout(canvasListItem, BoxLayout.Y_AXIS));
        canvasListItem.add(new JLabel(canvas.getName()));
        CanvasPanel canvasPreviewPanel = new CanvasPanel(canvas.getShapes(), clientModel);
        JPanel holder = new JPanel();
        canvasPreviewPanel.setPreferredSize(new Dimension(100,100));
        holder.add(canvasPreviewPanel);
        canvasListItem.add(holder);
        canvasListItem.setPreferredSize(new Dimension(150,150));
        canvasPreviewPanel.addMouseListener(new MouseInputAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                homescreenController.switchCanvas(canvas.getName());
            }
            
        });
        return canvasListItem;
    }

    @Override
    public void update() {
        this.setCanvases(clientModel.getCanvases());
    }
    
}
