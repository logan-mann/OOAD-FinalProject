package com.naiflogan.finalproject.backend.finalprojectbackend.observer;

public class LoggingEvent extends Event {

    private LoggingSeverity severity;

    public LoggingEvent(String summary, LoggingSeverity severity) {
        this.summary = summary;
        this.severity = severity;
    }

    public String getSummary() {
        return severity.toString() + " - " + this.summary;
    }
    
}
