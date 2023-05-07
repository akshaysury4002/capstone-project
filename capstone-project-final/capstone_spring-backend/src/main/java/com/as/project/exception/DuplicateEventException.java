package com.as.project.exception;

public class DuplicateEventException extends RuntimeException {
    
    public DuplicateEventException(String msg)
    {
        super("duplicate booking");
    }

}
