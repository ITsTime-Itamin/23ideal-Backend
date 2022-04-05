package com.example.itaminbackend.domain.board.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class BoardConstants {

    @Getter
    @RequiredArgsConstructor
    public enum EBoardController{
        eLocationIdPath("/{id}"),
        eGetMethod("get"),
        eDeleteMethod("delete"),
        eUpdateMethod("update");
        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EBoardResponseMessage{
        eCreateSuccess("게시판을 작성했습니다.");
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
}
