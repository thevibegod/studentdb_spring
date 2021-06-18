package com.example.studentDB.Exceptions;

public class InsufficientParameters extends RuntimeException{
    public InsufficientParameters(String message) {
        super(message);
    }
}
