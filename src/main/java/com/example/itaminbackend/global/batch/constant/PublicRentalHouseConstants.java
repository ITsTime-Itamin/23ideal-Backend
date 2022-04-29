package com.example.itaminbackend.global.batch.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PublicRentalHouseConstants {

    @Getter
    @AllArgsConstructor
    public enum ESignguCode {
        JONGNO_GU("110"),
        JUNG_GU("140"),
        YONGSAN_GU("170"),
        SEONGDONG_GU("200"),
        GWANGJIN_GU("215"),
        DONGDAEMUN_GU("230"),
        JUNGNANG_GU("260"),
        SEONGBUK_GU("290"),
        GANGBUK_GU("305"),
        DOBONG_GU("320"),
        NOWON_GU("350"),
        EUNPYEONG_GU("380"),
        SEODAEMUN_GU("410"),
        MAPO_GU("440"),
        YANGCHEON_GU("470"),
        GANGSEO_GU("500"),
        GURO_GU("530"),
        GEUMCHEON_GU("545"),
        YEONGDEUNGPO_GU("560"),
        DONGJAK_GU("590"),
        GWANAK_GU("620"),
        SEOCHO_GU("650"),
        GANGNAM_GU("680"),
        SONGPA_GU("710"),
        GANGDONG_GU("740");

        private final String code;
    }
}
