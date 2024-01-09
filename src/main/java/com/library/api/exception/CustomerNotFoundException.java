package com.library.api.exception;

import java.io.Serial;

public class CustomerNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = -3395693852865349037L;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
