package com.example.itaminbackend.domain.scrap.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = ScrapMapperSupport.class)
public abstract class ScrapDto {

    @Getter
    @NoArgsConstructor
    @ApiModel(description = "게시판 스크랩 많은 순 조회 위한 객체")
    public static class BoardInquiryByScrapRankingResponse {
        private Long boardId;
        private String boardTitle;
        private LocalDateTime createdDate;
        private Long scrapCount;

        @QueryProjection
        public BoardInquiryByScrapRankingResponse(Long boardId, String boardTitle, LocalDateTime createdDate,
                                                  Long scrapCount) {
            this.boardId = boardId;
            this.boardTitle = boardTitle;
            this.createdDate = createdDate;
            this.scrapCount = scrapCount;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ApiModel(description = "유저의 스크랩 생성/삭제 요청 객체")
    public static class ScrapChangeRequest {

        @NotNull(message = "스크랩 하고자하는 diaryId를 입력해 주세요.")
        @ApiModelProperty(notes = "diaryId를 입력해 주세요.")
        private Long diaryId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "유저의 스크랩 생성/삭제 응답 객체")
    public static class ScrapChangeResponse {

        private Long scrapId;
        private boolean isCreated;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "유저의 스크랩 수 응답 객체")
    public static class ScrapCountResponse {
        private Long scrapCount;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ApiModel(description = "해당 유저의 해당 다이어리 스크랩 여부 조회 요청 객체")
    public static class ScrapOrNotRequest {

        @NotNull(message = "diaryId를 입력해 주세요.")
        @ApiModelProperty(notes = "diaryId를 입력해 주세요.")
        private Long diaryId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "해당 유저의 해당 다이어리 스크랩 여부 조회 응답 객체")
    public static class ScrapOrNotResponse {

        boolean isScraped;

    }


}
