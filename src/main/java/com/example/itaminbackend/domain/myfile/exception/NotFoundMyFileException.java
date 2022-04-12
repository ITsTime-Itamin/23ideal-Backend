package com.example.itaminbackend.domain.myfile.exception;


import com.example.itaminbackend.domain.myfile.constant.MyFileConstants;

public class NotFoundMyFileException extends IllegalArgumentException{
    public NotFoundMyFileException() {
        super(MyFileConstants.EMyFileExceptionMessage.eNotFoundMyFileExceptionMessage.getMessage());
    }
}
