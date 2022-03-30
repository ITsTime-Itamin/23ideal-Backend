package com.example.itaminbackend.infra.file.exception;

public class FileExtensionException extends IllegalArgumentException {
    public FileExtensionException(String s) {
        super(s);
    }
}
