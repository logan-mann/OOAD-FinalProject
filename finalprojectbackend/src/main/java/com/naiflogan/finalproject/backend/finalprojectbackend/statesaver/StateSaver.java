package com.naiflogan.finalproject.backend.finalprojectbackend.statesaver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naiflogan.finalproject.backend.finalprojectbackend.Canvas;


public class StateSaver {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static String filename = "canvasState.json";

    public static void saveCanvasState(Map<String, Canvas> canvases) {

        File file = new File(filename);
        try {
            objectMapper.writeValue(file, canvases);
        } catch (Exception e) {
            System.out.println(e);
        }
 
    }

    public static Map<String, Canvas> loadCanvasState() {
        File file = new File(filename);
        if (file.exists()) {
            try {
                TypeReference<Map<String, Canvas>> typeRef = 
                new TypeReference<Map<String,Canvas>>() {
                    
                };
                return objectMapper.readValue(file, typeRef);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return new HashMap<>();
    }
    
}
