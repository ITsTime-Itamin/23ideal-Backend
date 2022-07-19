package com.example.itaminbackend.domain.comment.dto;

import com.example.itaminbackend.domain.comment.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ApiModel(description = "댓글 삭제를 위한 요청 객체")
    public static class DeleteRequest {
        @ApiModelProperty(notes = "댓글 id를 입력해주세요.")
        private Long commentId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "댓글 작성을 위한 응답 객체")
    public static class GetResponse {
        private Long commentId;
        private String content;
        private Long userId;
        private String userName;
        private List<GetResponse> children = new ArrayList<>();
        private LocalDateTime createdDate;

        public GetResponse(Long commentId, String content, Long userId, String userName, LocalDateTime createdDate) {
            this.commentId = commentId;
            this.content = content;
            this.userId = userId;
            this.userName = userName;
            this.createdDate = createdDate;
        }

        public static GetResponse convertCommentToDto(Comment comment) {

            return comment.isDeleted() == true ?
                    new GetResponse(comment.getCommentId(), "삭제된 댓글입니다.", null, null, null) :
                    new GetResponse(comment.getCommentId(), comment.getContent(), comment.getUser().getUserId(), comment.getUser().getName(), comment.getCreatedDate());
        }
    }

}
