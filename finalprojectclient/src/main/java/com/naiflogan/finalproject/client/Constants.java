package com.naiflogan.finalproject.client;

/**
 * Constants class, contains constant that holds the url for the backend API
 */
public class Constants {

    /*
        reads the value of environment variable CLOUDCANVAS_BACKEND_URL to get backend url
        for localhost backend, set env variable to http://localhost:8080
        for backend hosted on AWS, set env variable to http://44.193.28.254:8080
    */
    public final static String backendApiUrl = System.getenv("CLOUDCANVAS_BACKEND_URL");
}
