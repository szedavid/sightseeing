package com.szedavid.sightseeing.client;

import com.szedavid.sightseeing.client.exception.BadRequestException;
import com.szedavid.sightseeing.client.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                return new BadRequestException();   // todo test
            case 404:
                return new NotFoundException();   // todo test
            default:
                return new Exception("Generic error");
        }
    }
}