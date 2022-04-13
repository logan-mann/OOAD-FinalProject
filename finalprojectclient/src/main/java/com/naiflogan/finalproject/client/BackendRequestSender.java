package com.naiflogan.finalproject.client;

import com.naiflogan.finalproject.client.requests.AddCanvasRequest;
import com.naiflogan.finalproject.client.requests.AddShapeRequest;
import com.naiflogan.finalproject.client.requests.CreateAccountRequest;
import com.naiflogan.finalproject.client.requests.LoginRequest;

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

    public void createAccount(CreateAccountRequest accRequest) {
        String url = Constants.backendApiUrl + "/create_account";
        String res = this.restTemplate.postForObject(url, accRequest, String.class);
        System.out.println(res);
    }

    public void login(LoginRequest loginRequest) {
        String url = Constants.backendApiUrl + "/login";
        String res = this.restTemplate.postForObject(url, loginRequest, String.class);
        System.out.println(res);
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
