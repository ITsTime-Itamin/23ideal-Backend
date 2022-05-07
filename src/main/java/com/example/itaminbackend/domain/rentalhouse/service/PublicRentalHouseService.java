package com.example.itaminbackend.domain.rentalhouse.service;

import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;
import com.example.itaminbackend.domain.rentalhouse.entity.PublicRentalHouse;

import java.util.List;

public interface PublicRentalHouseService {
    void saveAll(List<PublicRentalHouse> publicRentalHouseList);
    void deleteAll();
    PublicRentalHouseCountResponse getPublicRentalHouseCount();
}
