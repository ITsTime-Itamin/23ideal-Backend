package com.example.itaminbackend.domain.board.exception;

import com.example.itaminbackend.domain.image.exception.ImageException;

import static com.example.itaminbackend.domain.board.constant.BoardConstants.BoardExceptionList.NOT_BOARD_WRITER;
import static com.example.itaminbackend.domain.board.constant.BoardConstants.BoardExceptionList.NOT_FOUND_BOARD;

public class NotFoundBoardException extends BoardException {
    public NotFoundBoardException() {
        super(NOT_FOUND_BOARD.getErrorCode(),
                NOT_FOUND_BOARD.getHttpStatus(),
                NOT_FOUND_BOARD.getMessage()
        );
    }
}
