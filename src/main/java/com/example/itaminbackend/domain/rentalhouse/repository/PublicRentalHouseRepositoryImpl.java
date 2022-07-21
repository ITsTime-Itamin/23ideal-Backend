package com.example.itaminbackend.domain.rentalhouse.repository;

import com.example.itaminbackend.domain.rentalhouse.constant.PublicRentalHouseConstants.ESignguCode;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseDetailResponse;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.WantedPublicRentalHouseResponse;
import com.example.itaminbackend.domain.rentalhouse.dto.QPublicRentalHouseDto_PublicRentalHouseCountResponse;
import com.example.itaminbackend.domain.rentalhouse.dto.QPublicRentalHouseDto_PublicRentalHouseDetailResponse;
import com.example.itaminbackend.domain.rentalhouse.dto.QPublicRentalHouseDto_WantedPublicRentalHouseResponse;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.itaminbackend.domain.rentalhouse.entity.QPublicRentalHouse.publicRentalHouse;

public class PublicRentalHouseRepositoryImpl implements PublicRentalHouseRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PublicRentalHouseRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public PublicRentalHouseCountResponse findAllPublicRentalHouseCount() {
        return queryFactory
                .select(new QPublicRentalHouseDto_PublicRentalHouseCountResponse(
                        getSignguCount(ESignguCode.JONGNO_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.JUNG_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.YONGSAN_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.SEONGDONG_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.GWANGJIN_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.DONGDAEMUN_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.JUNGNANG_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.SEONGBUK_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.GANGBUK_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.DOBONG_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.NOWON_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.EUNPYEONG_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.SEODAEMUN_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.MAPO_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.YANGCHEON_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.GANGSEO_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.GURO_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.GEUMCHEON_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.YEONGDEUNGPO_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.DONGJAK_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.GWANAK_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.SEOCHO_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.GANGNAM_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.SONGPA_GU.getCode()).sum(),
                        getSignguCount(ESignguCode.GANGDONG_GU.getCode()).sum()
                ))
                .from(publicRentalHouse)
                .fetchOne();
    }

    private NumberExpression<Integer> getSignguCount(String signguCode) {
        return publicRentalHouse.signguCode.
                when(signguCode).then(new Integer(1)).
                otherwise(new Integer(0));
    }

    @Override
    public Page<WantedPublicRentalHouseResponse> findWantedPublicRentalHouse(String sigunguCode, Pageable pageable){
        List<WantedPublicRentalHouseResponse> content= queryFactory
                .select(new QPublicRentalHouseDto_WantedPublicRentalHouseResponse(publicRentalHouse.publicRentalHouseId,
                        publicRentalHouse.hsmpNm,
                        publicRentalHouse.insttNm,
                        publicRentalHouse.signguCode,
                        publicRentalHouse.signguNm,
                        publicRentalHouse.rnAdres,
                        publicRentalHouse.houseTyNm,
                        publicRentalHouse.bassRentGtn,
                        publicRentalHouse.bassRentGtn))
                .from(publicRentalHouse)
                .where(publicRentalHouse.signguCode.eq(sigunguCode))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<WantedPublicRentalHouseResponse> countQuery=queryFactory
                .select(new QPublicRentalHouseDto_WantedPublicRentalHouseResponse(publicRentalHouse.publicRentalHouseId,
                        publicRentalHouse.hsmpNm,
                        publicRentalHouse.insttNm,
                        publicRentalHouse.signguCode,
                        publicRentalHouse.signguNm,
                        publicRentalHouse.rnAdres,
                        publicRentalHouse.houseTyNm,
                        publicRentalHouse.bassRentGtn,
                        publicRentalHouse.bassRentGtn))
                .from(publicRentalHouse)
                .where(publicRentalHouse.signguCode.eq(sigunguCode));

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());

    }

    @Override
    public PublicRentalHouseDetailResponse findPublicRentalHouseDetail(Long publicRentalHouseId){
        return queryFactory
                .select(new QPublicRentalHouseDto_PublicRentalHouseDetailResponse(publicRentalHouse.publicRentalHouseId,
                        publicRentalHouse.hsmpSn,
                        publicRentalHouse.insttNm,
                        publicRentalHouse.brtcCode,
                        publicRentalHouse.brtcNm,
                        publicRentalHouse.signguCode,
                        publicRentalHouse.signguNm,
                        publicRentalHouse.hsmpNm,
                        publicRentalHouse.rnAdres,
                        publicRentalHouse.pnu,
                        publicRentalHouse.competDe,
                        publicRentalHouse.hshldCo,
                        publicRentalHouse.suplyTyNm,
                        publicRentalHouse.styleNm,
                        publicRentalHouse.suplyPrvuseAr,
                        publicRentalHouse.suplyCmnuseAr,
                        publicRentalHouse.houseTyNm,
                        publicRentalHouse.heatMthdDetailNm,
                        publicRentalHouse.buldStleNm,
                        publicRentalHouse.elvtrInstlAtNm,
                        publicRentalHouse.parkngCo,
                        publicRentalHouse.bassRentGtn,
                        publicRentalHouse.bassMtRntchrg,
                        publicRentalHouse.bassCnvrsGtnLmt))
                .from(publicRentalHouse)
                .where(publicRentalHouse.publicRentalHouseId.eq(publicRentalHouseId))
                .fetchOne();
    }

}
