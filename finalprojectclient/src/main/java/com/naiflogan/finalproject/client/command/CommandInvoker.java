package com.naiflogan.finalproject.client.command;


//Invoker as part of COMMAND PATTERN, handles executing commands
public class CommandInvoker {

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
