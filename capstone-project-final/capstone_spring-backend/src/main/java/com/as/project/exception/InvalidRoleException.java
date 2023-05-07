package com.as.project.exception;

public class InvalidRoleException extends RuntimeException {
    
     
    public InvalidRoleException(String msg)
    {
        super("booking not found");
    }

}
