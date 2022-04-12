package com.naiflogan.finalproject.backend.finalprojectbackend.util;


import com.naiflogan.finalproject.backend.finalprojectbackend.logging.Logger;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.LoggingEvent;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.LoggingSeverity;

public class Util {

    public static void log(LoggingSeverity sev, String message) {
        Logger.getInstance().update(new LoggingEvent(message, sev));
    }
    
}
