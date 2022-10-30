package com.example.homework28emp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmpoyeeStorageFullException extends RuntimeException{
    public EmpoyeeStorageFullException() {
    }

    public EmpoyeeStorageFullException(String message) {
        super(message);
    }

    public EmpoyeeStorageFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmpoyeeStorageFullException(Throwable cause) {
        super(cause);
    }

    public EmpoyeeStorageFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
