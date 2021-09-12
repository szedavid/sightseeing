package com.szedavid.sightseeing.client.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 400:
                return new RemoteBadRequestException();
            case 404:
                return new RemoteNotFoundException();
            default:
                return new Exception("Generic error");
        }
    }
}