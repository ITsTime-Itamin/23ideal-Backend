package com.example.itaminbackend.global.batch.service;

import com.example.itaminbackend.global.batch.domain.PublicRentalHouse;

import java.util.List;

public interface PublicRentalHouseService {
    void saveAll(List<PublicRentalHouse> publicRentalHouseList);
    void deleteAll();
}
