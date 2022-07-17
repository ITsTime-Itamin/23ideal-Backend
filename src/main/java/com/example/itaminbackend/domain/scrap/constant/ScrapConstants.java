package com.example.itaminbackend.domain.scrap.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ScrapConstants {
    @Getter
    @RequiredArgsConstructor
    public enum EScrapResponseMessage{
        SCRAP_COUNT_SUCCESS("스크랩 수를 조회했습니다."),
        CREATE_SCRAP_SUCCESS("스크랩을 완료했습니다."),
        DELETE_SCRAP_SUCCESS("스크랩을 취소했습니다."),
        SCRAP_WHETHER_SUCCESS("스크랩 여부를 확인했습니다."),
        GET_ALL_BOARDS_BY_SCRAP_RANKING("모든 게시판을 스크랩 랭" +
                "" +
                "킹 순으로 조회합니다."),
        GET_SCRAPED_BOARDS_BY_USER("해당 유저의 스크랩한 게시판 목록 조회");
        private final String message;
    }
}

