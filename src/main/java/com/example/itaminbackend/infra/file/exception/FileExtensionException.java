package com.example.itaminbackend.infra.file.exception;

import static com.example.itaminbackend.infra.file.constant.FileConstants.FileExceptionList.FILE_EXTENSION;

public class FileExtensionException extends FileException {
    public FileExtensionException() {
        super(FILE_EXTENSION.getErrorCode(),
                FILE_EXTENSION.getHttpStatus(),
                FILE_EXTENSION.getMessage()
        );
    }
}
