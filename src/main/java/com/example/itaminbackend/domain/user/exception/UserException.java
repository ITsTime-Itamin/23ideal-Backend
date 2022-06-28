package com.example.itaminbackend.domain.user.exception;

import com.example.itaminbackend.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class UserException extends ApplicationException {
    protected UserException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
