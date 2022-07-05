package com.example.itaminbackend.domain.comment.exception;

import static com.example.itaminbackend.domain.comment.constant.CommentConstants.CommentExceptionList.NOT_FOUND_COMMENT;

public class NotFoundCommentException extends CommentException {
    public NotFoundCommentException() {
        super(NOT_FOUND_COMMENT.getErrorCode(),
                NOT_FOUND_COMMENT.getHttpStatus(),
                NOT_FOUND_COMMENT.getMessage()
        );
    }
}