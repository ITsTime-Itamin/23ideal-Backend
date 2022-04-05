package com.example.itaminbackend.domain.board.dto;

import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import com.example.itaminbackend.global.util.Enum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
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
        private final Long boardId;
    }
}
