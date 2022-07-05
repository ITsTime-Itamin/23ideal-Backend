package com.example.itaminbackend.domain.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class CommentDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "댓글 작성을 위한 요청 객체")
    public static class CreateRequest {
        @ApiModelProperty(notes = "부모 댓글 id를 입력해주세요.")
        private Long parentCommentId;

        @NotNull(message = "게시판 id를 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 id를 입력해 주세요.")
        private Long boardId;

        @NotBlank(message = "댓글 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "댓글 내용을 입력해 주세요.")
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "댓글 작성을 위한 응답 객체")
    public static class CreateResponse {
        private Long commentId;
    }
}
