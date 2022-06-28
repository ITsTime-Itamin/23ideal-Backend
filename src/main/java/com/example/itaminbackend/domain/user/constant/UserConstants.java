package com.example.itaminbackend.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class UserConstants {

    @Getter
    @RequiredArgsConstructor
    public enum UserExceptionList {
        NOT_FOUND_LOGIN_ID("U0001", HttpStatus.NOT_FOUND, "해당 loginId로 User를 찾을 수 없습니다."),
        NOT_FOUND_EMAIL("U0002", HttpStatus.NOT_FOUND, "해당 email로 User를 찾을 수 없습니다.");

        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EUserResponseMessage{
        LOGIN_SUCCESS("로그인에 성공했습니다.");
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EToken{
        eRefreshToken("RT:");
        private final String message;
    }

    @Getter
    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

}
