package com.example.itaminbackend.domain.comment.service;

import com.example.itaminbackend.domain.comment.dto.CommentDto;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateResponse;
import com.example.itaminbackend.domain.comment.entity.Comment;

public interface CommentService {

    CreateResponse createComment(CreateRequest createRequest);
    Comment validateCommentId(Long commentId);

}
