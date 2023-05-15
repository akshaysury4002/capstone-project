package com.as.project.exception;

public class FeedbackNotFoundException extends RuntimeException {
    
    public FeedbackNotFoundException(String msg)
    {
        super("duplicate booking");
    }

}
