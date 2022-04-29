package com.example.itaminbackend.global.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicRentalHouseResponse<T> {

    private Integer hsmpSn;
    private T insttNm;
    private String brtcCode;
    private String brtcNm;
    private String signguCode;
    private String signguNm;
    private T hsmpNm;
    private String rnAdres;
    private T pnu;
    private T competDe;
    private T hshldCo;
    private T suplyTyNm;
    private T styleNm;
    private T suplyPrvuseAr;
    private T suplyCmnuseAr;
    private T houseTyNm;
    private T heatMthdDetailNm;
    private T buldStleNm;
    private T elvtrInstlAtNm;
    private T parkngCo;
    private T bassRentGtn;
    private T bassMtRntchrg;
    private T bassCnvrsGtnLmt;

}
