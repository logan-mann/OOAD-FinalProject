package com.naiflogan.finalproject.backend.finalprojectbackend.observer;

/**
 * Base Event class for OBSERVER PATTERN
 */
public abstract class Event {
    protected String summary;

    public String getSummary() {
        return this.summary;
    }
}
