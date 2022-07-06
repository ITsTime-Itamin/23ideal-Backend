package com.example.itaminbackend.domain.board.dto;

import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.comment.dto.CommentDto;
import com.example.itaminbackend.domain.comment.dto.CommentDto.GetResponse;
import com.example.itaminbackend.domain.image.entity.Image;
import com.example.itaminbackend.global.util.Enum;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public abstract class BoardDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "게시판 등록을 위한 요청 객체")
    public static class CreateRequest {
        @NotBlank(message = "게시판 제목을 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 제목을 입력해 주세요.")
        private String title;

        @NotBlank(message = "게시판 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 내용을 입력해 주세요.")
        private String content;

        @NotBlank(message = "게시판 유형을 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 유형을 입력해 주세요.")
        @Enum(enumClass = EBoardType.class)
        private String boardType;

        @NotBlank(message = "게시판 마감일자를 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 마감일자를 입력해 주세요.")
        @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}"
                , message = "날짜 형식이 맞지 않습니다. yyyy-mm-ss hh:mm:ss 형식으로 입력해주세요.")
        private String deadLineDate;

        private List<MultipartFile> files;
    }

    @Getter
    @AllArgsConstructor
    @ApiModel(description = "게시판 등록을 위한 응답 객체")
    public static class CreateResponse {
        private Long boardId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "게시판 등록을 위한 수정 객체")
    public static class UpdateRequest {

        @NotBlank(message = "게시판 id를 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 id를 입력해 주세요.")
        private String boardId;

        @NotBlank(message = "게시판 제목을 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 제목을 입력해 주세요.")
        private String title;

        @NotBlank(message = "게시판 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 내용을 입력해 주세요.")
        private String content;

        @NotBlank(message = "게시판 유형을 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 유형을 입력해 주세요.")
        @Enum(enumClass = EBoardType.class)
        private String boardType;

        @NotBlank(message = "게시판 마감일자를 입력해 주세요.")
        @ApiModelProperty(notes = "게시판 마감일자를 입력해 주세요.")
        @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}"
                , message = "날짜 형식이 맞지 않습니다. yyyy-mm-ss hh:mm:ss 형식으로 입력해주세요.")
        private String deadLineDate;

        private List<MultipartFile> files;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @ApiModel(description = "게시판 수정을 위한 응답 객체")
    public static class UpdateResponse {
        private Long boardId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @ApiModel(description = "게시판 조회를 위한 응답 객체")
    public static class GetDetailResponse {
        private Long boardId;
        private String title;
        private String content;
        private String boardType;
        private LocalDateTime createdDate;
        private LocalDateTime deadLineDate;
        private List<String> imageKeys;
        private String userName;
    }

    @Getter
    @Builder
    @ApiModel(description = "게시판 전체 조회를 위한 응답 객체")
    public static class GetAllResponse {
        private Long boardId;
        private String title;
        private String content;
        private LocalDateTime createdDate;
        private String imageKey;
        private String userName;

        @QueryProjection
        public GetAllResponse(Long boardId, String title, String content, LocalDateTime createdDate, String imageKey, String userName) {
            this.boardId = boardId;
            this.title = title;
            this.content = content;
            this.createdDate = createdDate;
            this.imageKey = imageKey;
            this.userName = userName;
        }
    }


}
