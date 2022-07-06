package com.example.itaminbackend.domain.comment.service;

import com.example.itaminbackend.domain.comment.dto.CommentDto;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateResponse;
import com.example.itaminbackend.domain.comment.dto.CommentDto.DeleteRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.GetResponse;
import com.example.itaminbackend.domain.comment.entity.Comment;

import java.util.List;

public interface CommentService {

    CreateResponse createComment(CreateRequest createRequest);
    Comment deleteComment(DeleteRequest deleteRequest);
    List<GetResponse> getAllCommentsByBoardId(Long boardId);
    Comment validateCommentId(Long commentId);

}
