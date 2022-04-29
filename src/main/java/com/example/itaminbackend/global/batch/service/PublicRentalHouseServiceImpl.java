package com.example.itaminbackend.global.batch.service;

import com.example.itaminbackend.global.batch.domain.PublicRentalHouse;
import com.example.itaminbackend.global.batch.repository.PublicRentalHouseRepository;
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
}
