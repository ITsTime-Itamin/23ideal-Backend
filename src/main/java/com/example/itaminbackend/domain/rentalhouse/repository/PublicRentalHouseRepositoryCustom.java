package com.example.itaminbackend.domain.rentalhouse.repository;

import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;

public interface PublicRentalHouseRepositoryCustom {
    PublicRentalHouseCountResponse findAllPublicRentalHouseCount();
}
