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


/**
 * This view displays a scrollable menu of available canvases and allows the user to select from them
 * This is part of MVC PATTERN, so implements MVC View class
 * references state contained in ClientModel
 */
public class SelectCanvasView extends JPanel implements View {

    private HomescreenController homescreenController;
    private ClientModel clientModel;
    private JPanel canvasList;

    public SelectCanvasView(Map<String,Canvas> canvases, HomescreenController homescreenController, ClientModel clientModel) {
        //Set member variables
        this.homescreenController = homescreenController;
        this.clientModel = clientModel;
        //this.clientModel.attach(this);
        //Set panel layout manager (Vertical Box Layout)
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Initialize canvasList JPanel (Holds all canvas selection panels)
        canvasList = new JPanel();
        canvasList.setLayout(new BoxLayout(canvasList, BoxLayout.Y_AXIS));
        canvasList.setAutoscrolls(true);

        //Create a scroll view for canvas list
        JScrollPane scrollPane = new JScrollPane(canvasList);
        scrollPane.setPreferredSize(new Dimension(200,500));
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        //Add Panel to hold scroll pane
        JPanel scrollHolder = new JPanel();
        scrollHolder.add(scrollPane);
        this.add(scrollHolder);

        //Button to navigate to create canvas screen
        JButton gotoCreateCanvasButton = new JButton("Create New Canvas");
        gotoCreateCanvasButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                homescreenController.goToCreateCanvasView();
            }
            
        });
        this.add(gotoCreateCanvasButton);
        //Initialize canvas list to current set of canvases
        setCanvasList(canvases);
    }


    public void setCanvasList(Map<String, Canvas> canvases) {
        //Clear current components from panel
        canvasList.removeAll();
        //For each canvas in the entry set, create a component for it, add it to canvasList panel
        for (Entry<String, Canvas> entry : canvases.entrySet()) {
            Canvas canvas = entry.getValue();
            canvasList.add(getCanvasListItem(canvas));
        }
    }


    //Helper function to build list item component for a canvas
    private JPanel getCanvasListItem(Canvas canvas) {
        //Create outer panel to house subcomponents
        JPanel canvasListItem = new JPanel();
        canvasListItem.setLayout(new BoxLayout(canvasListItem, BoxLayout.Y_AXIS));
        //Add a label with canvas name
        canvasListItem.add(new JLabel(canvas.getName()));
        //Create a CanvasPanel to display canvas contents
        CanvasPanel canvasPreviewPanel = new CanvasPanel(canvas.getShapes(), clientModel);
        canvasPreviewPanel.setPreferredSize(new Dimension(100,100));

        //Panel to hold CanvasPanel
        JPanel holder = new JPanel();
        holder.add(canvasPreviewPanel);
        canvasListItem.add(holder);
        canvasListItem.setPreferredSize(new Dimension(150,150));

        //Add adapter to switch canvases when clicked
        canvasPreviewPanel.addMouseListener(new MouseInputAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                homescreenController.switchCanvas(canvas.getName());
            }
            
        });
        return canvasListItem;
    }

    //When updated, reset the canvas list
    @Override
    public void update() {
        this.setCanvasList(clientModel.getCanvases());
    }
    
}
