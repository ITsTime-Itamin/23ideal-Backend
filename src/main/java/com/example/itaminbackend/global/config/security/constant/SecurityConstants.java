package com.example.itaminbackend.global.config.security.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class SecurityConstants {
    @Getter
    @RequiredArgsConstructor
    public enum SecurityExceptionList {
        REQUIRED_LOGGED_IN("SS0001", HttpStatus.UNAUTHORIZED, "해당 서비스는 로그인이 필요한 서비스입니다.");

        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }
}
