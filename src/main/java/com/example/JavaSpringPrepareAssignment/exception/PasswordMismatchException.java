package com.example.JavaSpringPrepareAssignment.exception;

public class PasswordMismatchException extends IllegalArgumentException{
    public PasswordMismatchException(String s) {
        super(s);
    }
}
