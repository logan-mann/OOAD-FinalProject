package com.naiflogan.finalproject.client.command;


/*
    The Command interface, its implementing classes, and the Invoker implement the COMMAND PATTERN
    COMMAND PATTERN is used to decouple logic of the client->backend functions from homescreen and auth controllers
*/
public interface Command {
    public void execute();
}
