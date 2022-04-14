package com.naiflogan.finalproject.backend.finalprojectbackend.controller;


import com.naiflogan.finalproject.backend.finalprojectbackend.database.User;
import com.naiflogan.finalproject.backend.finalprojectbackend.database.UserRepository;
import com.naiflogan.finalproject.backend.finalprojectbackend.jwt.JwtUtils;
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
public class AuthApiController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;


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

    

        try {
            User user = userRepo.getUserByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getHashedPassword())) {
                String jwt = jwtUtils.generateJwt(user);
                LoginResponse response = new LoginResponse(jwt, null);
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



    
    
}
