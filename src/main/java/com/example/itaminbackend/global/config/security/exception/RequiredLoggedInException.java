package com.example.itaminbackend.global.config.security.exception;

import com.example.itaminbackend.domain.user.exception.UserException;
import com.example.itaminbackend.global.config.security.constant.SecurityConstants;

import static com.example.itaminbackend.global.config.security.constant.SecurityConstants.SecurityExceptionList.REQUIRED_LOGGED_IN;

public class RequiredLoggedInException extends UserException {
    public RequiredLoggedInException() {
        super(REQUIRED_LOGGED_IN.getErrorCode(),
                REQUIRED_LOGGED_IN.getHttpStatus(),
                REQUIRED_LOGGED_IN.getMessage()
        );
    }
}
