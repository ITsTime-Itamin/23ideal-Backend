package com.example.itaminbackend.infra.file.exception;

import static com.example.itaminbackend.infra.file.constant.FileConstants.FileExceptionList.FILE_LOAD_FAILED;

public class FileLoadFailedException extends FileException {
    public FileLoadFailedException() {
        super(FILE_LOAD_FAILED.getErrorCode(),
                FILE_LOAD_FAILED.getHttpStatus(),
                FILE_LOAD_FAILED.getMessage()
        );
    }
}
