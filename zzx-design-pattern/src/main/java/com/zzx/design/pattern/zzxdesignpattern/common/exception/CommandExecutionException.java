package com.zzx.design.pattern.zzxdesignpattern.common.exception;

public class CommandExecutionException extends RuntimeException {
    
    public CommandExecutionException(String message) {
        super(message);
    }
    
    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CommandExecutionException(Throwable cause) {
        super(cause);
    }
}