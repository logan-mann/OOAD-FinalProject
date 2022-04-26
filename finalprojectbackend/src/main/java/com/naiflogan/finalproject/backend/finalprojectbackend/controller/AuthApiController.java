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

/**
 * RestController to respond to incoming User Authentication requests
 * Implements Subject as part of OBSERVER PATTERN, used for logging
 */
@RestController
public class AuthApiController implements Subject {

    //UserRepository for retrieving/adding user credientials
    @Autowired
    private UserRepository userRepo;

    //Password encoder for hashing passwords for storage in database
    @Autowired
    private PasswordEncoder passwordEncoder;

    //JwtUtils instance for issuing/verifying Json Web Tokens
    @Autowired
    private JwtUtils jwtUtils;

    //List observers, initialize as a list containing Logger singleton
    private List<Observer> observers = new ArrayList<Observer>(Arrays.asList(Logger.getInstance()));


    //Method to create accounts
    @PostMapping("/create_account")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {

        final String username = createAccountRequest.getUsername();
        final String password = createAccountRequest.getPassword();

        //Notify logger
        notifyObservers(new LoggingEvent("New CreateAccount Request: Username: " + username, LoggingSeverity.INFO));

        try {
            //try to create user in user repo w/ username and encoded password
            userRepo.createUser(username, passwordEncoder.encode(password));
            return new ResponseEntity<>("Account creation successful.", HttpStatus.OK);
        } catch (Exception e) {
            //If fails, print error and return error message
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Account creation failed.", HttpStatus.OK);
        }


    }

    //Method to log a user in
    @PostMapping( value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        //Notify logger of request
        notifyObservers(new LoggingEvent("New Login Request: Username: " + username, LoggingSeverity.INFO));

        try {
            //Try to retrieve a user entity with given username
            UserEntity userEntity = userRepo.getUserByUsername(username);
            if (userEntity != null && passwordEncoder.matches(password, userEntity.getHashedPassword())) {
                //If username exists and the password in db matches the one passed, generate JWT and send User object with user info
                String jwt = jwtUtils.generateJwt(userEntity);
                User user = new User(userEntity.getUsername(), jwt);
                LoginResponse response = new LoginResponse(user, null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            //Otherwise, login failed, return failed response w/ error
            LoginResponse response = new LoginResponse(null, "Login failed.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LoginResponse response = new LoginResponse(null, "Login failed.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


    }

    //Register an observer as part of OBSERVER PATTERN
    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
        
    }
    
    //Remove an observer as part of OBSERVER PATTERN
    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }
       
    //Notify all observers as part of OBSERVER PATTERN
    @Override
    public void notifyObservers(Event event) {
        for (Observer observer : observers) {
            observer.update(event);
        }        
    }



    
    
}
