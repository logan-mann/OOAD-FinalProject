package com.naiflogan.finalproject.backend.finalprojectbackend.config;

import java.util.Map;

import javax.annotation.PreDestroy;

import com.naiflogan.finalproject.backend.finalprojectbackend.Canvas;
import com.naiflogan.finalproject.backend.finalprojectbackend.statesaver.StateSaver;

import org.springframework.beans.factory.annotation.Autowired;

//Class to save canvas state on application termination
public class TerminateBean {

    @Autowired
    Map<String, Canvas> canvases;

    @PreDestroy
    public void saveState() {
        StateSaver.saveCanvasState(canvases);
    }

}
