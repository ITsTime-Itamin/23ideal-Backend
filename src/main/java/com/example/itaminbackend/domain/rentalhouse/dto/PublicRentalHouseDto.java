package com.example.itaminbackend.domain.rentalhouse.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public abstract class PublicRentalHouseDto {

    @Getter
    @Builder
    @ApiModel(description = "서울 모든 구의 공공임대주택 개수 응답 객체")
    public static class PublicRentalHouseCountResponse {
        private int jongNoGuCount;
        private int jungGuCount;
        private int yongSanGuCount;
        private int seongDongGuCount;
        private int gwangJinGuCount;
        private int dongDaeMunCount;
        private int jungNangGuCount;
        private int seongBukGuCount;
        private int gangBukGuCount;
        private int doBongGuCount;
        private int noWonGuCount;
        private int eunPyeongGuCount;
        private int seoDaeMunGuCount;
        private int maPoGuCount;
        private int yangCheonGuCount;
        private int gangSeoGuCount;
        private int guRoGuCount;
        private int geumCheonGuCount;
        private int yeongDeungPoGuCount;
        private int dongJakGuCount;
        private int gwanAkGuCount;
        private int seoChoGuCount;
        private int gangNamGuCount;
        private int songPaGuCount;
        private int gangDongGuCount;

        @QueryProjection
        public PublicRentalHouseCountResponse(int jongNoGuCount, int jungGuCount, int yongSanGuCount, int seongDongGuCount,
                                              int gwangJinGuCount, int dongDaeMunCount, int jungNangGuCount, int seongBukGuCount,
                                              int gangBukGuCount, int doBongGuCount, int noWonGuCount, int eunPyeongGuCount,
                                              int seoDaeMunGuCount, int maPoGuCount, int yangCheonGuCount, int gangSeoGuCount,
                                              int guRoGuCount, int geumCheonGuCount, int yeongDeungPoGuCount, int dongJakGuCount,
                                              int gwanAkGuCount, int seoChoGuCount, int gangNamGuCount, int songPaGuCount, int gangDongGuCount) {
            this.jongNoGuCount = jongNoGuCount;
            this.jungGuCount = jungGuCount;
            this.yongSanGuCount = yongSanGuCount;
            this.seongDongGuCount = seongDongGuCount;
            this.gwangJinGuCount = gwangJinGuCount;
            this.dongDaeMunCount = dongDaeMunCount;
            this.jungNangGuCount = jungNangGuCount;
            this.seongBukGuCount = seongBukGuCount;
            this.gangBukGuCount = gangBukGuCount;
            this.doBongGuCount = doBongGuCount;
            this.noWonGuCount = noWonGuCount;
            this.eunPyeongGuCount = eunPyeongGuCount;
            this.seoDaeMunGuCount = seoDaeMunGuCount;
            this.maPoGuCount = maPoGuCount;
            this.yangCheonGuCount = yangCheonGuCount;
            this.gangSeoGuCount = gangSeoGuCount;
            this.guRoGuCount = guRoGuCount;
            this.geumCheonGuCount = geumCheonGuCount;
            this.yeongDeungPoGuCount = yeongDeungPoGuCount;
            this.dongJakGuCount = dongJakGuCount;
            this.gwanAkGuCount = gwanAkGuCount;
            this.seoChoGuCount = seoChoGuCount;
            this.gangNamGuCount = gangNamGuCount;
            this.songPaGuCount = songPaGuCount;
            this.gangDongGuCount = gangDongGuCount;
        }
    }

    @Getter
    @Builder
    @ApiModel("해당 구의 대략적인 공고 정보 응답 객체")
    public static class WantedPublicRentalHouseResponse{
        private Long publicRentalHouseId;
        private String hsmpNm; //단지 식별자
        private String insttNm; //기관명
        private String signguCode;
        private String signguNm;
        private String rnAdres; //도로명주소
        private String houseTyNm; //주택유형명
        private Integer bassRentGtn; //기본 임대보증금
        private Integer bassMtRntchrg; //기본 월 임대료

        @QueryProjection
        public WantedPublicRentalHouseResponse(Long publicRentalHouseId, String hsmpNm, String insttNm, String signguCode, String signguNm,
                                               String rnAdres, String houseTyNm, Integer bassRentGtn, Integer bassMtRntchrg){
            this.publicRentalHouseId=publicRentalHouseId;
            this.hsmpNm=hsmpNm;
            this.insttNm=insttNm;
            this.signguCode=signguCode;
            this.signguNm=signguNm;
            this.rnAdres=rnAdres;
            this.houseTyNm=houseTyNm;
            this.bassRentGtn=bassRentGtn;
            this.bassMtRntchrg=bassMtRntchrg;
        }
    }

    @Getter
    @Builder
    @ApiModel("해당 공고의 자세한 정보 응답 객체")
    public static class PublicRentalHouseDetailResponse{
        private Long publicRentalHouseId;
        private Integer hsmpSn;
        private String insttNm;
        private String brtcCode;
        private String brtcNm;
        private String signguCode;
        private String signguNm;
        private String hsmpNm;
        private String rnAdres;
        private String pnu;
        private String competDe;
        private Integer hshldCo;
        private String suplyTyNm;
        private String styleNm;
        private Double suplyPrvuseAr;
        private Double suplyCmnuseAr;
        private String houseTyNm;
        private String heatMthdDetailNm;
        private String buldStleNm;
        private String elvtrInstlAtNm;
        private Integer parkngCo;
        private Integer bassRentGtn;
        private Integer bassMtRntchrg;
        private Integer bassCnvrsGtnLmt;

        @QueryProjection
        public PublicRentalHouseDetailResponse(Long publicRentalHouseId, Integer hsmpSn, String insttNm, String brtcCode,
                                               String brtcNm, String signguCode, String signguNm, String hsmpNm, String rnAdres,
                                               String pnu, String competDe, Integer hshldCo, String suplyTyNm,
                                               String styleNm, Double suplyPrvuseAr, Double suplyCmnuseAr, String houseTyNm,
                                               String heatMthdDetailNm, String buldStleNm, String elvtrInstlAtNm, Integer parkngCo,
                                               Integer bassRentGtn, Integer bassMtRntchrg, Integer bassCnvrsGtnLmt){
            this.publicRentalHouseId=publicRentalHouseId;
            this.hsmpSn=hsmpSn;
            this.insttNm=insttNm;
            this.brtcCode=brtcCode;
            this.brtcNm=brtcNm;
            this.signguCode=signguCode;
            this.signguNm=signguNm;
            this.hsmpNm=hsmpNm;
            this.rnAdres=rnAdres;
            this.pnu=pnu;
            this.competDe=competDe;
            this.hshldCo=hshldCo;
            this.suplyTyNm=suplyTyNm;
            this.styleNm=styleNm;
            this.suplyPrvuseAr=suplyPrvuseAr;
            this.suplyCmnuseAr=suplyCmnuseAr;
            this.houseTyNm=houseTyNm;
            this.heatMthdDetailNm=heatMthdDetailNm;
            this.buldStleNm=buldStleNm;
            this.elvtrInstlAtNm=elvtrInstlAtNm;
            this.parkngCo=parkngCo;
            this.bassRentGtn=bassRentGtn;
            this.bassMtRntchrg=bassMtRntchrg;
            this.bassCnvrsGtnLmt=bassCnvrsGtnLmt;
        }
    }

}
