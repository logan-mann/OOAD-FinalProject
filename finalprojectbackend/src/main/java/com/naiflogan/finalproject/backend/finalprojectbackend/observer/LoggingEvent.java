package com.naiflogan.finalproject.backend.finalprojectbackend.observer;

/**
 * LoggingEvent subclass extends base Event class to provide additional Logging information
 * Part of OBSERVER PATTERN
 */
public class LoggingEvent extends Event {

    //Includes a LoggingSeverity in summary
    private LoggingSeverity severity;

    public LoggingEvent(String summary, LoggingSeverity severity) {
        this.summary = summary;
        this.severity = severity;
    }

    public String getSummary() {
        return severity.toString() + " - " + this.summary;
    }
    
}
