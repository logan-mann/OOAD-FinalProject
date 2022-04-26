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


/**
 * RestController to handle requests to backend service
 * Implements Subject as part of OBSERVER PATTERN, used for Logging
 */
@RestController
public class BackendApiController implements Subject {

    //List of canvases
    @Autowired
    private Map<String, Canvas> canvases;

    //JWT Utils instance for verifying Json Web Tokens
    @Autowired
    private JwtUtils jwtUtils;
    
    //List of observers as part of OBSERVER PATTERN
    private List<Observer> observers = new ArrayList<Observer>(Arrays.asList(Logger.getInstance()));


    //Method to add a shape to a canvas
    @PostMapping("/add_shape")
    public ResponseEntity<String> addShape(@RequestBody AddShapeRequest addShapeRequest) {
        
        //Get shape, jwt, and canvasName from request object
        final String canvasName = addShapeRequest.getCanvasName();
        final Shape shape = addShapeRequest.getShape();
        final String jwt = addShapeRequest.getJwt();

        //Validate token, if invalid, deny request
        if (!jwtUtils.validateJwtToken(jwt)) {
            return new ResponseEntity<>("Invalid JWT.", HttpStatus.OK);
        }

        //Get the username from JWT if valid
        final String username = jwtUtils.getUsernameFromJwt(jwt);

        //Log request
        notifyObservers(new LoggingEvent("New AddShapeRequest: {canvasName: " + canvasName + ", username: " + username + ", shapeType: " + shape.getClass().getName() + "}", LoggingSeverity.INFO));

        //If canvas with canvasName passed doesnt exist, deny request
        if (!canvases.containsKey(canvasName)) {
            notifyObservers(new LoggingEvent("AddShapeRequest: {canvasName: " + canvasName + ", username: " + username + "} failed, canvas not found", LoggingSeverity.ERROR));
            return new ResponseEntity<>("Canvas not found.", HttpStatus.OK);
        }
        Canvas canvas = canvases.get(canvasName);
        //If the canvas exists and is public, or the user is allowed to edit the canvas, place the shape, return success
        if (canvas.isPublic() || canvas.getAllowedUsernames().contains(username)) {
            canvas.addShape(shape);
            notifyObservers(new LoggingEvent("AddShapeRequest: {canvasName: " + canvasName + ", username: " + username + "} succeeded, shape added", LoggingSeverity.INFO));
            return new ResponseEntity<>("Shape successfully added!", HttpStatus.OK);
        }
        //Otherwise, deny request
        notifyObservers(new LoggingEvent("AddShapeRequest: {canvasName: " + canvasName + ", username: " + username + "} failed, user not authorized.", LoggingSeverity.ERROR));
        return new ResponseEntity<>("Not authorized to add to this canvas.", HttpStatus.OK);
    }

    //Method to add a canvas
    @PostMapping("/add_canvas")
    public ResponseEntity<String> addCanvas(@RequestBody AddCanvasRequest addCanvasRequest) {
        //Get JWT, isPublic, and canvasName from request object
        final String canvasName = addCanvasRequest.getCanvasName();
        final String jwt = addCanvasRequest.getJwt();
        final boolean isPublic = addCanvasRequest.isPublic();

        //Validate token, if invalid, deny request
        if (!jwtUtils.validateJwtToken(jwt)) {
            return new ResponseEntity<>("Invalid JWT.", HttpStatus.OK);
        }

        //Get username from JWT if valid
        final String username = jwtUtils.getUsernameFromJwt(jwt);
        
        //Log request
        notifyObservers(new LoggingEvent("New AddCanvasRequest: {canvasName: " + canvasName + ", username: " + username + "}", LoggingSeverity.INFO));

        //If canvas w/ canvasName already exists, deny request
        if (canvases.containsKey(canvasName)) {
            notifyObservers(new LoggingEvent("AddCanvasRequest: {canvasName: " + canvasName + ", username: " + username + "} denied, canvas already exists", LoggingSeverity.INFO));
            return new ResponseEntity<>("Canvas: " + canvasName + " already exists.", HttpStatus.OK);
        }
        //Create new list of allowed usernames, add username of creator
        List<String> allowedUsernames = new ArrayList<>();
        allowedUsernames.add(username);

        //Create new canvas object, add to map
        Canvas newCanvas = new Canvas(canvasName, isPublic, allowedUsernames);
        canvases.put(canvasName, newCanvas);
        return new ResponseEntity<>("Canvas successfully created!", HttpStatus.OK);
    }

    //Takes in a jwt, if this is a valid token, returns all the canvases that user can see
    @PostMapping("/get_canvases")
    Map<String, Canvas> getCanvases(@RequestBody String jwt) {
        //Validate token, if invalid, return empty map
        if (!jwtUtils.validateJwtToken(jwt)) {
            return new HashMap<>();
        }
        //If valid, get username from token
        String username = jwtUtils.getUsernameFromJwt(jwt);

        //Create return map
        Map<String, Canvas> resultMap = new HashMap<>();
        //For each entry in the global canvas map, append it to result map if user can access canvas
        for (Entry<String, Canvas> entry : this.canvases.entrySet()) {
            String canvasName = entry.getKey();
            Canvas canvas = entry.getValue();
            if (canvas.getAllowedUsernames().contains(username) || canvas.isPublic()) {
                resultMap.put(canvasName, canvas);
            }
        }
        //return result map
        return resultMap;

    }


    //Methods to add, remove, and notify observers as part of OBSERVER PATTERN

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
