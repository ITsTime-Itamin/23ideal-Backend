package com.example.itaminbackend.domain.board.exception;

import com.example.itaminbackend.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class BoardException extends ApplicationException {
    protected BoardException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
