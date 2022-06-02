package com.example.itaminbackend.domain.board.dto;

import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.image.entity.Image;
import com.example.itaminbackend.global.util.Enum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
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
        private List<String> imageKeys;
    }


}
