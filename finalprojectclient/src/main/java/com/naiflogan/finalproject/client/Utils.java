package com.naiflogan.finalproject.client;

public class Utils {

    public static String capitalizeFirstLetter(String str) {
        String output = str.substring(0, 1).toUpperCase() + str.substring(1);
        return output;
    }
    
}
