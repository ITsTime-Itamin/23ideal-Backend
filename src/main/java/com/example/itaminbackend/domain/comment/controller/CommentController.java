package com.example.itaminbackend.domain.comment.controller;


import com.example.itaminbackend.domain.comment.constant.CommentConstants.ECommentResponseMessage;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateResponse;
import com.example.itaminbackend.domain.comment.service.CommentService;
import com.example.itaminbackend.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/comments")
@Api(tags = "Comment API")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateResponse>> updateBoard(@Valid @RequestBody CreateRequest createRequest){
        return ResponseEntity.ok(ResponseDto.create(ECommentResponseMessage.CREATE_COMMENT_SUCCESS.getMessage(), this.commentService.createComment(createRequest)));
    }
}
