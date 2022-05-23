package com.example.itaminbackend.infra.file.exception;

import com.example.itaminbackend.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class FileException extends ApplicationException {
    protected FileException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
