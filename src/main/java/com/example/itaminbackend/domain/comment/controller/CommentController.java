package com.example.itaminbackend.domain.comment.controller;

import com.example.itaminbackend.domain.comment.constant.CommentConstants.ECommentResponseMessage;
import com.example.itaminbackend.domain.comment.dto.CommentDto;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateResponse;
import com.example.itaminbackend.domain.comment.dto.CommentDto.DeleteRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.GetResponse;
import com.example.itaminbackend.domain.comment.service.CommentService;
import com.example.itaminbackend.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/comments")
@Api(tags = "Comment API")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateResponse>> createComment(@Valid @RequestBody CreateRequest createRequest){
        return ResponseEntity.ok(ResponseDto.create(ECommentResponseMessage.CREATE_COMMENT_SUCCESS.getMessage(), this.commentService.createComment(createRequest)));
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteComment(@Valid @RequestBody DeleteRequest deleteRequest){
        this.commentService.deleteComment(deleteRequest);
        return ResponseEntity.ok(ResponseDto.create(ECommentResponseMessage.DELETE_COMMENT_SUCCESS.getMessage()));
    }

    @ApiOperation(value = "게시판의 댓글 목록 조회", notes = "게시판의 댓글 목록을 조회합니다.")
    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto<List<GetResponse>>> getAllComments(@PathVariable Long boardId){
        return ResponseEntity.ok(ResponseDto.create(ECommentResponseMessage.GET_ALL_DETAIL_COMMENTS_SUCCESS.getMessage(),
                this.commentService.getAllCommentsByBoardId(boardId)));
    }
}
