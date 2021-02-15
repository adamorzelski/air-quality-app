package com.example.air_quality_app.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("Not found exception");
    }
}
