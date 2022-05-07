package com.example.itaminbackend.domain.rentalhouse.repository;

import com.example.itaminbackend.domain.rentalhouse.constant.PublicRentalHouseConstants.ESignguCode;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;
import com.example.itaminbackend.domain.rentalhouse.dto.QPublicRentalHouseDto_PublicRentalHouseCountResponse;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

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

}
