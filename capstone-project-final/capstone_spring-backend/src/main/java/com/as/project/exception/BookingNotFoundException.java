package com.as.project.exception;

public class BookingNotFoundException extends RuntimeException {
    
    public BookingNotFoundException(String msg)
    {
        super("booking not found");
    }
}
