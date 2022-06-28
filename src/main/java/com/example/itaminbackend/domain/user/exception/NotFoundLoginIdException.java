package com.example.itaminbackend.domain.user.exception;

import com.example.itaminbackend.domain.image.exception.ImageException;

import static com.example.itaminbackend.domain.user.constant.UserConstants.UserExceptionList.NOT_FOUND_LOGIN_ID;

public class NotFoundLoginIdException extends UserException {
    public NotFoundLoginIdException() {
        super(NOT_FOUND_LOGIN_ID.getErrorCode(),
                NOT_FOUND_LOGIN_ID.getHttpStatus(),
                NOT_FOUND_LOGIN_ID.getMessage()
        );
    }
}
