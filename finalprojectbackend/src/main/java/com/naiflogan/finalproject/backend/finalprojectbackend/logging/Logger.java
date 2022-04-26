package com.naiflogan.finalproject.backend.finalprojectbackend.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.naiflogan.finalproject.backend.finalprojectbackend.observer.Event;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.LoggingEvent;
import com.naiflogan.finalproject.backend.finalprojectbackend.observer.Observer;


/*
    Handle logging of backend API activity
    Logs any events observed by the logger, outputs to file w/ date and time of event
    Utilizes OBSERVER and SINGLETON Patterns
*/
public class Logger implements Observer {

    private static Logger loggerInstance = new Logger();

    public void update(Event event) {
        //Only deal with logging events
        if (event.getClass() == LoggingEvent.class) {
            //Get date and filename
            LocalDate currDate = LocalDate.now();
            String filename = "Logger-Output-" + currDate.toString();
            File file = new File(filename);
            
            //Create or open file and append message
            try {
                if (!file.exists()) {
                    file.createNewFile();
                } 
                FileWriter writer = new FileWriter(filename, true);
                Writer output = new BufferedWriter(writer);
                output.append(LocalDateTime.now() + ": " + event.getSummary() + "\n");
                output.close();
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }

    }

    //Get singleton instance
    public static Logger getInstance() {
        return loggerInstance;
    }




    
}
