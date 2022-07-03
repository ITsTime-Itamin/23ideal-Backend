package com.example.itaminbackend.domain.board.exception;

import com.example.itaminbackend.domain.image.exception.ImageException;

import static com.example.itaminbackend.domain.board.constant.BoardConstants.BoardExceptionList.NOT_BOARD_WRITER;
import static com.example.itaminbackend.domain.board.constant.BoardConstants.BoardExceptionList.NOT_FOUND_BOARD;

public class NotBoardWriterException extends ImageException {
    public NotBoardWriterException() {
        super(NOT_BOARD_WRITER.getErrorCode(),
                NOT_BOARD_WRITER.getHttpStatus(),
                NOT_BOARD_WRITER.getMessage()
        );
    }
}
