package com.naiflogan.finalproject.backend.finalprojectbackend.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.naiflogan.finalproject.backend.finalprojectbackend.user.User;
import com.naiflogan.finalproject.backend.finalprojectbackend.database.UserEntity;
import com.naiflogan.finalproject.backend.finalprojectbackend.database.UserRepository;
import com.naiflogan.finalproject.backend.finalprojectbackend.jwt.JwtUtils;
import com.naiflogan.finalproject.backend.finalprojectbackend.logging.Logger;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.Event;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.LoggingEvent;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.LoggingSeverity;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.Observer;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.Subject;
import com.naiflogan.finalproject.backend.finalprojectbackend.request.CreateAccountRequest;
import com.naiflogan.finalproject.backend.finalprojectbackend.request.LoginRequest;
import com.naiflogan.finalproject.backend.finalprojectbackend.responses.LoginResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthApiController implements Subject {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    private List<Observer> observers = new ArrayList<Observer>(Arrays.asList(Logger.getInstance()));


    @PostMapping("/create_account")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {

        final String username = createAccountRequest.getUsername();
        final String password = createAccountRequest.getPassword();

    

        try {
            userRepo.createUser(username, passwordEncoder.encode(password));
            return new ResponseEntity<>("Account creation successful.", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Account creation failed.", HttpStatus.OK);
        }


    }

    @PostMapping( value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        notifyObservers(new LoggingEvent("New Login Request: Username: " + username, LoggingSeverity.INFO));


        try {
            UserEntity userEntity = userRepo.getUserByUsername(username);
            if (userEntity != null && passwordEncoder.matches(password, userEntity.getHashedPassword())) {
                String jwt = jwtUtils.generateJwt(userEntity);
                User user = new User(userEntity.getUsername(), jwt);
                LoginResponse response = new LoginResponse(user, null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            LoginResponse response = new LoginResponse(null, "Login failed.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LoginResponse response = new LoginResponse(null, "Login failed.");
            return new ResponseEntity<>(response, HttpStatus.OK);
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

    @Override
    public void notifyObservers(Event event) {
        for (Observer observer : observers) {
            observer.update(event);
        }        
    }



    
    
}
