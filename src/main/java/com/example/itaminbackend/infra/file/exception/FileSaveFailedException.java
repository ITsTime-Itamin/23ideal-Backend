package com.example.itaminbackend.infra.file.exception;

import static com.example.itaminbackend.infra.file.constant.FileConstants.FileExceptionList.FILE_SAVE_FAILED;

public class FileSaveFailedException extends FileException {
    public FileSaveFailedException() {
        super(FILE_SAVE_FAILED.getErrorCode(),
                FILE_SAVE_FAILED.getHttpStatus(),
                FILE_SAVE_FAILED.getMessage()
        );
    }
}
