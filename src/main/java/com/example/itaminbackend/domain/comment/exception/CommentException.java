package com.example.itaminbackend.domain.comment.exception;

import com.example.itaminbackend.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class CommentException extends ApplicationException {
    protected CommentException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}


