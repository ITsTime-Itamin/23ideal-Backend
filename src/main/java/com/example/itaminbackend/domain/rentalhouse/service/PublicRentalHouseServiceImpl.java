package com.example.itaminbackend.domain.rentalhouse.service;

import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;
import com.example.itaminbackend.domain.rentalhouse.repository.PublicRentalHouseRepository;
import com.example.itaminbackend.domain.rentalhouse.entity.PublicRentalHouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicRentalHouseServiceImpl implements PublicRentalHouseService{

    private final PublicRentalHouseRepository publicRentalHouseRepository;

    @Override
    public void saveAll(List<PublicRentalHouse> publicRentalHouseList) {
        this.publicRentalHouseRepository.saveAll(publicRentalHouseList);
    }

    @Override
    public void deleteAll() {
        this.publicRentalHouseRepository.deleteAll();
    }

    @Override
    public PublicRentalHouseCountResponse getPublicRentalHouseCount() {
        return this.publicRentalHouseRepository.findAllPublicRentalHouseCount();
    }
}
