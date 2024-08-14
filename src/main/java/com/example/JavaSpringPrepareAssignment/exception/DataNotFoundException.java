package com.example.JavaSpringPrepareAssignment.exception;

public class DataNotFoundException extends IllegalArgumentException{
    public DataNotFoundException(String s) {
        super(s);
    }
}
