package com.example.Library.Management.System.exception;

public class BookNotAvailableException extends RuntimeException{

    public BookNotAvailableException(String message){
        super(message);
    }
}
