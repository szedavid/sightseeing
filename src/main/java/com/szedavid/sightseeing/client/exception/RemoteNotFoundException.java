package com.szedavid.sightseeing.client.exception;

public class RemoteNotFoundException extends Exception{

    public RemoteNotFoundException() {
        super("Remote JSON endpoint not found");
    }

}
