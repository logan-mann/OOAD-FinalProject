package com.naiflogan.finalproject.backend.finalprojectbackend.observer;

/**
 * Subject interface as part of OBSERVER PATTERN
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Event event);
}
