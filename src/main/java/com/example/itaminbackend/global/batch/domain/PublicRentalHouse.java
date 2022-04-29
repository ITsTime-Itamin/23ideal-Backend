package com.example.itaminbackend.global.batch.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicRentalHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
