package com.example.itaminbackend.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class GlobalConstants {

    @Getter
    @RequiredArgsConstructor
    public enum JWTExceptionList {
        UNKNOWN_ERROR("J0001", "JWT토큰을 입력해주세요."),
        WRONG_TYPE_TOKEN("J0002", "잘못된 JWT 서명입니다."),
        EXPIRED_TOKEN("J0003", "만료된 토큰입니다."),
        UNSUPPORTED_TOKEN("J0004", "지원되지 않는 토큰입니다."),
        ACCESS_DENIED("J0005","접근이 거부되었습니다."),
        WRONG_TOKEN("J0006","JWT 토큰이 잘못되었습니다.");

        private final String errorCode;
        private final String message;

    }
}
