package com.example.studentDB.Exceptions;

public class ObjectAlreadyExists extends RuntimeException {
    public ObjectAlreadyExists(String message) {
        super(message);
    }
}
