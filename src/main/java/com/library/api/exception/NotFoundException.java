package com.library.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = -3395693852865349037L;

    public NotFoundException(String message) {
        super(message);
    }
}
