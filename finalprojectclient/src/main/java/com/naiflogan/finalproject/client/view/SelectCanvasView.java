package com.naiflogan.finalproject.client.view;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;

import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.canvas.CanvasPanel;
import com.naiflogan.finalproject.client.controller.HomescreenController;
import com.naiflogan.finalproject.client.model.ClientModel;

public class SelectCanvasView extends JPanel implements View {

    private static int count = 0;

    private Map<String, Canvas> canvases;
    private HomescreenController homescreenController;

    public SelectCanvasView(Map<String,Canvas> canvases, HomescreenController homescreenController) {
        this.canvases = canvases;
        this.homescreenController = homescreenController;
        renderUi();
    }

    private void renderUi() {
            JPanel canvasList = new JPanel();
            canvasList.setLayout(new BoxLayout(canvasList, BoxLayout.Y_AXIS));
            this.removeAll();
            for (Entry<String, Canvas> entry : canvases.entrySet()) {
                Canvas canvas = entry.getValue();
                canvasList.add(getCanvasListItem(canvas));
            }
            this.add(canvasList);
    }


    //Helper function to build list item for canvas
    private JPanel getCanvasListItem(Canvas canvas) {
        JPanel canvasListItem = new JPanel();
        canvasListItem.setLayout(new BoxLayout(canvasListItem, BoxLayout.Y_AXIS));
        canvasListItem.add(new JLabel(canvas.getName()));
        CanvasPanel canvasPreviewPanel = new CanvasPanel(canvas.getShapes());
        JPanel holder = new JPanel();
        canvasPreviewPanel.setPreferredSize(new Dimension(100,100));
        holder.add(canvasPreviewPanel);
        canvasListItem.add(holder);
        canvasListItem.setPreferredSize(new Dimension(150,150));
        canvasPreviewPanel.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.out.println("Mouse Clicked");
                homescreenController.switchCanvas(canvas.getName());        

                
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseDragged(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseMoved(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }
            
        });
        return canvasListItem;
    }

    @Override
    public void update() {
        this.renderUi();
    }
    
}
