package com.example.demo.exceptions;

public class BandServiceException extends RuntimeException{

    private static final long serialVersionUID = 1348771109171435607L;

    public BandServiceException(String message)
    {
        super(message);
    }
}
