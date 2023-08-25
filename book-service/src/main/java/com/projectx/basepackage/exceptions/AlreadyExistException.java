package com.projectx.basepackage.exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }
}
