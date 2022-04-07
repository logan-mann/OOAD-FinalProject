package com.naiflogan.finalproject.backend.finalprojectbackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.naiflogan.finalproject.backend.finalprojectbackend.Canvas;
import com.naiflogan.finalproject.backend.finalprojectbackend.requests.AddCanvasRequest;
import com.naiflogan.finalproject.backend.finalprojectbackend.requests.AddShapeRequest;
import com.naiflogan.finalproject.backend.finalprojectbackend.shapes.Coordinate;
import com.naiflogan.finalproject.backend.finalprojectbackend.shapes.Line;
import com.naiflogan.finalproject.backend.finalprojectbackend.shapes.Shape;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendApiController {

    @Autowired
    private Map<String, Canvas> canvases;

    @PostMapping("/add_shape")
    public ResponseEntity<String> addShape(@RequestBody AddShapeRequest addShapeRequest) {
        final String canvasName = addShapeRequest.getCanvasName();
        final Shape shape = addShapeRequest.getShape();
        final String username = addShapeRequest.getUsername();
        if (!canvases.containsKey(canvasName)) {
            return new ResponseEntity<>("Canvas not found.", HttpStatus.OK);
        }
        Canvas canvas = canvases.get(canvasName);
        if (canvas.isPublic() || canvas.getAllowedUsernames().contains(username)) {
            canvas.addShape(shape);
            return new ResponseEntity<>("Shape successfully added!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Not authorized to add to this canvas.", HttpStatus.OK);
    }

    @PostMapping("/add_canvas")
    public ResponseEntity<String> addCanvas(@RequestBody AddCanvasRequest addCanvasRequest) {
        final String canvasName = addCanvasRequest.getCanvasName();
        final String username = addCanvasRequest.getUsername();
        final boolean isPublic = addCanvasRequest.isPublic();


        if (canvases.containsKey(canvasName)) {
            return new ResponseEntity<>("Canvas: " + canvasName + " already exists.", HttpStatus.OK);
        }
        List<String> allowedUsernames = new ArrayList<>();
        allowedUsernames.add(username);

        Canvas newCanvas = new Canvas(canvasName, isPublic, allowedUsernames);
        canvases.put(canvasName, newCanvas);
        return new ResponseEntity<>("Canvas successfully created!", HttpStatus.OK);
    }

    @GetMapping("/get_canvases")
    Map<String, Canvas> getCanvases() {
        return canvases;
    }
}
