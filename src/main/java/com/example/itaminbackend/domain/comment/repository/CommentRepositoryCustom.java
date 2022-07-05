package com.example.itaminbackend.domain.comment.repository;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.comment.entity.Comment;

import java.util.Optional;

public interface CommentRepositoryCustom {
    Optional<Comment> findNotDeletedByCommentId(Long commentId);
}
