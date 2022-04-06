package com.example.itaminbackend.infra.file.exception;

public class FileSaveFailedException extends IllegalArgumentException {
    private static final String MESSAGE = "파일 저장에 실패했습니다.";
    public FileSaveFailedException() {super(MESSAGE);}
}
