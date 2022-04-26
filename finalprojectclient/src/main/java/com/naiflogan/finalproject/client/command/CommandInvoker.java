package com.naiflogan.finalproject.client.command;


//Invoker as part of COMMAND PATTERN, handles executing commands
public class CommandInvoker {

    //Invoker uses SINGLETON PATTERN, we only need a single invoker instance throughout client application
    private static CommandInvoker instance = new CommandInvoker();

    private Command command;

    private CommandInvoker() {
        this.command = new NullCommand();
    }

    public static CommandInvoker getInstance() {
        return instance;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        this.command.execute();
    }
    
}
