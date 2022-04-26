package com.naiflogan.finalproject.client.requestsender;

import com.naiflogan.finalproject.client.Constants;
import com.naiflogan.finalproject.client.request.CreateAccountRequest;
import com.naiflogan.finalproject.client.request.LoginRequest;
import com.naiflogan.finalproject.client.responses.LoginResponse;
import com.naiflogan.finalproject.client.user.User;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This class contains the logic needed for sending requests to the backend service relating to user authentication
 * Uses SINGLETON pattern, as we only need one instance in the client
 */
@Service
public class AuthRequestSender {
    //RestTemplate from Spring, used for sending requests + receiving responses
    private final RestTemplate restTemplate;

    //Instance for SINGLETON PATTERN
    private static AuthRequestSender instance = new AuthRequestSender();

    private AuthRequestSender() {
        this.restTemplate = new RestTemplate();
    }

    public static AuthRequestSender getInstance() {
        return instance;
    }

    /**
     * Sends CreateAccountRequest accRequest to backend
     */
    public void createAccount(CreateAccountRequest accRequest) {
        String url = Constants.backendApiUrl + "/create_account";
        String res = this.restTemplate.postForObject(url, accRequest, String.class);
        System.out.println(res);
    }

    /**
     * Sends LoginRequest to backend
     * Returns User object on successful login, or null on login failure
     */
    public User login(LoginRequest loginRequest) {
        String url = Constants.backendApiUrl + "/login";

        try {
            LoginResponse res = this.restTemplate.postForObject(url, loginRequest, LoginResponse.class);
            //If request was successful, return User object from response
            if (res.getError() == null) {
                return res.getUser();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //On failure, return null
        return null;
    }
}
