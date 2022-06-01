package com.example.itaminbackend.domain.image.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class ImageConstants {

    @Getter
    @RequiredArgsConstructor
    public enum ImageExceptionList {
        NOT_FOUND_IMAGE("I0001", HttpStatus.NOT_FOUND, "해당 imageId로 Image를 찾을 수 없습니다.");

        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }
}
