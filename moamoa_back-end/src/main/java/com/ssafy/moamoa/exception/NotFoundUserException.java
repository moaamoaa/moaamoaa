package com.ssafy.moamoa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundUserException extends NullPointerException {
    public NotFoundUserException(String message) {
        super(message);
    }
}