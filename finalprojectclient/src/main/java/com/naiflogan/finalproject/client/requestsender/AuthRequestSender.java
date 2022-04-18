package com.naiflogan.finalproject.client.requestsender;

import com.naiflogan.finalproject.client.Constants;
import com.naiflogan.finalproject.client.request.CreateAccountRequest;
import com.naiflogan.finalproject.client.request.LoginRequest;
import com.naiflogan.finalproject.client.responses.LoginResponse;
import com.naiflogan.finalproject.client.user.User;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthRequestSender {
    private final RestTemplate restTemplate;

    private static AuthRequestSender instance = new AuthRequestSender();

    private AuthRequestSender() {
        this.restTemplate = new RestTemplate();
    }

    public static AuthRequestSender getInstance() {
        return instance;
    }

    public void createAccount(CreateAccountRequest accRequest) {
        String url = Constants.backendApiUrl + "/create_account";
        String res = this.restTemplate.postForObject(url, accRequest, String.class);
        System.out.println(res);
    }

    /*
        Returns User object on successful login, or null on login failure
    */
    public User login(LoginRequest loginRequest) {
        String url = Constants.backendApiUrl + "/login";

        try {
            LoginResponse res = this.restTemplate.postForObject(url, loginRequest, LoginResponse.class);
            if (res.getError() == null) {
                System.out.println("Token: " + res.getUser().getJsonWebToken());
                return res.getUser();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
