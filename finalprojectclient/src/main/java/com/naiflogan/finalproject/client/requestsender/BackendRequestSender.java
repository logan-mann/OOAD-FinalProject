package com.naiflogan.finalproject.client.requestsender;

import java.util.Map;

import com.naiflogan.finalproject.client.Constants;
import com.naiflogan.finalproject.client.canvas.Canvas;
import com.naiflogan.finalproject.client.request.AddCanvasRequest;
import com.naiflogan.finalproject.client.request.AddShapeRequest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This class contains the logic needed for sending any requests not related to user authentication to the backend service
 * Uses SINGLETON pattern, as we only need one instance in the client
 */
@Service
public class BackendRequestSender {
    //RestTemplate from Spring, used for sending requests + receiving responses
    private final RestTemplate restTemplate;

    //One instance as part of SINGLETON PATTERN
    private static BackendRequestSender instance = new BackendRequestSender();

    private BackendRequestSender() {
        this.restTemplate = new RestTemplate();
    }

    public static BackendRequestSender getInstance() {
        return instance;
    }

    /**
     * Sends AddShapeRequest to backend service
     * @param shapeRequest
     */
    public void addShape(AddShapeRequest shapeRequest) {
        String url = Constants.backendApiUrl + "/add_shape";
        String res = this.restTemplate.postForObject(url, shapeRequest, String.class);
        System.out.println(res);
    }

    /**
     * Sends AddCanvasRequest to backend service
     * @param canvasRequest
     */
    public void addCanvas(AddCanvasRequest canvasRequest) {
        String url = Constants.backendApiUrl + "/add_canvas";
        String res = this.restTemplate.postForObject(url, canvasRequest, String.class);
        System.out.println(res);
    }

    /**
     * Sends a request to backend service to get most up-to-date canvas information
     * @param jwt Logged in user's JWT
     * @return Map<String, Canvas> of all canvases in the backend available to user
     */
    public Map<String, Canvas> getCanvases(String jwt) {
        String url = Constants.backendApiUrl + "/get_canvases";
        ParameterizedTypeReference<Map<String, Canvas>> responseType = 
        new ParameterizedTypeReference<Map<String, Canvas>>() {};

        
        RequestEntity<String> request = RequestEntity.post(url).accept(MediaType.APPLICATION_JSON).body(jwt);

        Map<String, Canvas> res = this.restTemplate.exchange(request, responseType).getBody();
        return res;
    }
}
