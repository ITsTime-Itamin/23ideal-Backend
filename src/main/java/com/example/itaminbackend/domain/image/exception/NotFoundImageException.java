package com.example.itaminbackend.domain.image.exception;


import static com.example.itaminbackend.domain.image.constant.ImageConstants.ImageExceptionList.NOT_FOUND_IMAGE;

public class NotFoundImageException extends ImageException{
    public NotFoundImageException() {
        super(NOT_FOUND_IMAGE.getErrorCode(),
                NOT_FOUND_IMAGE.getHttpStatus(),
                NOT_FOUND_IMAGE.getMessage()
        );
    }
}
