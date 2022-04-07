package com.naiflogan.finalproject.client;

import com.naiflogan.finalproject.client.requests.AddCanvasRequest;
import com.naiflogan.finalproject.client.requests.AddShapeRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BackendRequestSender {
    private final RestTemplate restTemplate;

    private static BackendRequestSender instance = new BackendRequestSender();

    private BackendRequestSender() {
        this.restTemplate = new RestTemplate();
    }

    public static BackendRequestSender getInstance() {
        return instance;
    }

    public void addShape(AddShapeRequest shapeRequest) {
        String url = Constants.backendApiUrl + "/add_shape";
        String res = this.restTemplate.postForObject(url, shapeRequest, String.class);

        //TODO - will change this to render result on GUI later
        System.out.println(res);
    }

    public void addCanvas(AddCanvasRequest canvasRequest) {
        String url = Constants.backendApiUrl + "/add_canvas";
        String res = this.restTemplate.postForObject(url, canvasRequest, String.class);

        //TODO - will change this to render result on GUI later
        System.out.println(res);
    }
}
