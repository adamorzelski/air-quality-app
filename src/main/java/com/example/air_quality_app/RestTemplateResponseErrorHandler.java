package com.example.air_quality_app;

import com.example.air_quality_app.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (clientHttpResponse.getStatusCode().series() == CLIENT_ERROR
                        || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            //TODO handle SERVER_ERROR
            throw new NotFoundException();
        } else if (clientHttpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            //TODO handle CLIENT_ERROR
            if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException();
            }
        }
    }
}
