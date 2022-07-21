package com.example.itaminbackend.domain.rentalhouse.repository;

import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublicRentalHouseRepositoryCustom {
    PublicRentalHouseCountResponse findAllPublicRentalHouseCount();
    Page<PublicRentalHouseDto.WantedPublicRentalHouseResponse> findWantedPublicRentalHouse(String sigunguCode, Pageable pageable);
    PublicRentalHouseDto.PublicRentalHouseDetailResponse findPublicRentalHouseDetail(Long publicRentalHouseId);
}
