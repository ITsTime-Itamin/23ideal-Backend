package com.example.itaminbackend.domain.user.exception;

import static com.example.itaminbackend.domain.user.constant.UserConstants.UserExceptionList.NOT_FOUND_EMAIL;

public class NotFoundEmailException extends UserException {
    public NotFoundEmailException() {
        super(NOT_FOUND_EMAIL.getErrorCode(),
                NOT_FOUND_EMAIL.getHttpStatus(),
                NOT_FOUND_EMAIL.getMessage()
        );
    }
}
