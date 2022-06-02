package com.example.itaminbackend.domain.board.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class BoardConstants {

    @Getter
    @RequiredArgsConstructor
    public enum EBoardController{
        LOCATION_ID_PATH("/{id}"),
        GET_METHOD("get"),
        DELETE_METHOD("delete"),
        UPDATE_METHOD("update");
        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EBoardResponseMessage{
        CREATE_BOARD_SUCCESS("게시판을 작성했습니다."),
        UPDATE_BOARD_SUCCESS("게시판을 수정했습니다."),
        GET_DETAIL_BOARD_SUCCESS("게시판을 조회했습니다."),
        DELETE_BOARD_SUCCESS("게시판을 삭제했습니다."),
        GET_ALL_DETAIL_BOARDS_SUCCESS("게시판을 작성 시간순으로 조회했습니다.");
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EBoardType{
        eNotice("공지사항"),
        ePromotion("홍보"),
        eFreeBoard("자유게시판"),
        eReview("후기"),
        eJobPosting("취업공고");
        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum BoardExceptionList {
        NOT_FOUND_BOARD("B0001", HttpStatus.NOT_FOUND, "해당 boardId로 Board를 찾을 수 없습니다.");

        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }
}
