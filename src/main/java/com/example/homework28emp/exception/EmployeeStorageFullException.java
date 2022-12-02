package com.example.homework28emp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeStorageFullException extends RuntimeException{
    public EmployeeStorageFullException() {
    }

    public EmployeeStorageFullException(String message) {
        super(message);
    }

    public EmployeeStorageFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeStorageFullException(Throwable cause) {
        super(cause);
    }

    public EmployeeStorageFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
