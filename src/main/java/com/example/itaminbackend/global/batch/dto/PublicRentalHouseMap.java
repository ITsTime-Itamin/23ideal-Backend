package com.example.itaminbackend.global.batch.dto;


import com.example.itaminbackend.domain.rentalhouse.entity.PublicRentalHouse;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import java.util.LinkedHashMap;

public class PublicRentalHouseMap<T> extends PropertyMap<PublicRentalHouseResponse, PublicRentalHouse> {

    private final LinkedHashMap linkedHashMap = new LinkedHashMap();

    @Override
    protected void configure() {
        Converter<Object, String> objectToString =
                ots -> ots.getSource().getClass().equals(linkedHashMap.getClass()) == true
                        ? null : String.valueOf(ots.getSource());

        Converter<Object, Integer> objectToInteger =
                oti -> oti.getSource().getClass().equals(linkedHashMap.getClass()) == true
                        ? null : Integer.valueOf(String.valueOf(oti.getSource()));

        Converter<Object, Double> objectToDouble =
                otd -> otd.getSource().getClass().equals(linkedHashMap.getClass()) == true
                        ? null : Double.valueOf(String.valueOf(otd.getSource()));

        map().setHsmpSn(source.getHsmpSn());
        using(objectToString).map(source.getInsttNm()).setInsttNm(null);
        map().setBrtcCode(source.getBrtcCode());
        map().setBrtcNm(source.getBrtcNm());
        map().setSignguCode(source.getSignguCode());
        map().setSignguNm(source.getSignguNm());
        using(objectToString).map(source.getHsmpNm()).setHsmpNm(null);
        map().setRnAdres(source.getRnAdres());
        using(objectToString).map(source.getPnu()).setPnu(null);

        using(objectToString).map(source.getCompetDe()).setCompetDe(null);
        using(objectToInteger).map(source.getHshldCo()).setHshldCo(null);
        using(objectToString).map(source.getSuplyTyNm()).setSuplyTyNm(null);
        using(objectToString).map(source.getStyleNm()).setStyleNm(null);
        using(objectToDouble).map(source.getSuplyPrvuseAr()).setSuplyPrvuseAr(null);
        using(objectToDouble).map(source.getSuplyCmnuseAr()).setSuplyCmnuseAr(null);

        using(objectToString).map(source.getHouseTyNm()).setHouseTyNm(null);
        using(objectToString).map(source.getHeatMthdDetailNm()).setHeatMthdDetailNm(null);
        using(objectToString).map(source.getBuldStleNm()).setBuldStleNm(null);
        using(objectToString).map(source.getElvtrInstlAtNm()).setElvtrInstlAtNm(null);

        using(objectToInteger).map(source.getParkngCo()).setParkngCo(null);
        using(objectToInteger).map(source.getBassRentGtn()).setBassRentGtn(null);
        using(objectToInteger).map(source.getBassMtRntchrg()).setBassMtRntchrg(null);
        using(objectToInteger).map(source.getBassCnvrsGtnLmt()).setBassCnvrsGtnLmt(null);

    }

}
