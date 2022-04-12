package com.naiflogan.finalproject.backend.finalprojectbackend.observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Event event);
}
