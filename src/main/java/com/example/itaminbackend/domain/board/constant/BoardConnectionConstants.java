package com.example.itaminbackend.domain.board.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class BoardConnectionConstants {
    @Getter
    @RequiredArgsConstructor
    public enum EDiaryConnectionResponseMessage{
        eGetDiaryDetailsSuccessMessage("다이어리를 상세히 조회했습니다.");
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EDiaryConnectionServiceImpl{
        eThirtyMinutes(30);
        private final int value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EHttpServletRequestAttribute{
        eUserId("userId");
        private final String attribute;
    }
}
