package com.naiflogan.finalproject.backend.finalprojectbackend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.naiflogan.finalproject.backend.finalprojectbackend.Canvas;
import com.naiflogan.finalproject.backend.finalprojectbackend.jwt.JwtUtils;
import com.naiflogan.finalproject.backend.finalprojectbackend.logging.Logger;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.Event;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.LoggingEvent;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.LoggingSeverity;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.Observer;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.Subject;
import com.naiflogan.finalproject.backend.finalprojectbackend.request.AddCanvasRequest;
import com.naiflogan.finalproject.backend.finalprojectbackend.request.AddShapeRequest;
import com.naiflogan.finalproject.backend.finalprojectbackend.shapes.Shape;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendApiController implements Subject {

    @Autowired
    private Map<String, Canvas> canvases;

    @Autowired
    private JwtUtils jwtUtils;
    
    private List<Observer> observers = new ArrayList<Observer>(Arrays.asList(Logger.getInstance()));


    @PostMapping("/add_shape")
    public ResponseEntity<String> addShape(@RequestBody AddShapeRequest addShapeRequest) {
        

        final String canvasName = addShapeRequest.getCanvasName();
        final Shape shape = addShapeRequest.getShape();
        final String jwt = addShapeRequest.getJwt();

        if (!jwtUtils.validateJwtToken(jwt)) {
            return new ResponseEntity<>("Invalid JWT.", HttpStatus.OK);
        }

        final String username = jwtUtils.getUsernameFromJwt(jwt);

        notifyObservers(new LoggingEvent("New AddShapeRequest: {canvasName: " + canvasName + ", username: " + username + ", shapeType: " + shape.getType() + "}", LoggingSeverity.INFO));

        if (!canvases.containsKey(canvasName)) {
            notifyObservers(new LoggingEvent("AddShapeRequest: {canvasName: " + canvasName + ", username: " + username + "} failed, canvas not found", LoggingSeverity.ERROR));
            return new ResponseEntity<>("Canvas not found.", HttpStatus.OK);
        }
        Canvas canvas = canvases.get(canvasName);
        if (canvas.isPublic() || canvas.getAllowedUsernames().contains(username)) {
            canvas.addShape(shape);
            notifyObservers(new LoggingEvent("AddShapeRequest: {canvasName: " + canvasName + ", username: " + username + "} succeeded, shape added", LoggingSeverity.INFO));
            return new ResponseEntity<>("Shape successfully added!", HttpStatus.OK);
        }
        notifyObservers(new LoggingEvent("AddShapeRequest: {canvasName: " + canvasName + ", username: " + username + "} failed, user not authorized.", LoggingSeverity.ERROR));
        return new ResponseEntity<>("Not authorized to add to this canvas.", HttpStatus.OK);
    }

    @PostMapping("/add_canvas")
    public ResponseEntity<String> addCanvas(@RequestBody AddCanvasRequest addCanvasRequest) {
        final String canvasName = addCanvasRequest.getCanvasName();
        final String jwt = addCanvasRequest.getJwt();

        if (!jwtUtils.validateJwtToken(jwt)) {
            return new ResponseEntity<>("Invalid JWT.", HttpStatus.OK);
        }

        final String username = jwtUtils.getUsernameFromJwt(jwt);
        final boolean isPublic = addCanvasRequest.isPublic();
        
        notifyObservers(new LoggingEvent("New AddCanvasRequest: {canvasName: " + canvasName + ", username: " + username + "}", LoggingSeverity.INFO));


        if (canvases.containsKey(canvasName)) {
            notifyObservers(new LoggingEvent("AddCanvasRequest: {canvasName: " + canvasName + ", username: " + username + "} denied, canvas already exists", LoggingSeverity.INFO));
            return new ResponseEntity<>("Canvas: " + canvasName + " already exists.", HttpStatus.OK);
        }
        List<String> allowedUsernames = new ArrayList<>();
        allowedUsernames.add(username);

        Canvas newCanvas = new Canvas(canvasName, isPublic, allowedUsernames);
        canvases.put(canvasName, newCanvas);
        return new ResponseEntity<>("Canvas successfully created!", HttpStatus.OK);
    }

    //Takes in a jwt, if this is a valid token, returns all the canvases that user can see
    @PostMapping("/get_canvases")
    Map<String, Canvas> getCanvases(@RequestBody String jwt) {
        if (!jwtUtils.validateJwtToken(jwt)) {
            return new HashMap<>();
        }
        String username = jwtUtils.getUsernameFromJwt(jwt);

        Map<String, Canvas> resultMap = new HashMap<>();

        for (Entry<String, Canvas> entry : this.canvases.entrySet()) {
            String canvasName = entry.getKey();
            Canvas canvas = entry.getValue();
            if (canvas.getAllowedUsernames().contains(username) || canvas.isPublic()) {
                resultMap.put(canvasName, canvas);
            }
        }
        return resultMap;

    }

    public void notifyObservers(Event event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
        
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);        
    }
}
