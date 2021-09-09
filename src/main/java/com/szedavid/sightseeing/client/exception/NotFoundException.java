package com.szedavid.sightseeing.client.exception;

public class NotFoundException extends Exception{
    public NotFoundException() {
        super("Endpoint not found");
    }
}
