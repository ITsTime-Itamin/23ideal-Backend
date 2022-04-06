package com.example.itaminbackend.infra.file.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class FileConstants {

    @Getter
    @RequiredArgsConstructor
    public enum ELocalFileServiceImpl{
        eImagePath("./images/"),
        eFileStringFormat("%s%s.%s");
        private final String value;
    }

}
