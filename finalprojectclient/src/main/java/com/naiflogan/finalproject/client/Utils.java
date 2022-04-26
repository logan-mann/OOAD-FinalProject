package com.naiflogan.finalproject.client;

/**
 * Utils class, holds any misc utility functions
 */
public class Utils {

    //Method to capitalize only the first leter of a string, lowercase the rest
    public static String capitalizeFirstLetter(String str) {
        String output = str.substring(0, 1).toUpperCase() + str.substring(1);
        return output;
    }
    
}
