package com.szedavid.sightseeing.client.exception;

// will not occur in this demo
public class RemoteBadRequestException extends Exception{
    public RemoteBadRequestException() {
        super("Bad request to remote server");
    }
}
