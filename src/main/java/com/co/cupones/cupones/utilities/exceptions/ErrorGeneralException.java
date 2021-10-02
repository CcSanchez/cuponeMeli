package com.co.cupones.cupones.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.INTERNAL_SERVER_ERROR
)
public class ErrorGeneralException extends Exception {

    public ErrorGeneralException(Throwable cause) {
        super(cause);
    }
}

