package com.example.itaminbackend.domain.rentalhouse.service;

import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;
import com.example.itaminbackend.domain.rentalhouse.repository.PublicRentalHouseRepository;
import com.example.itaminbackend.domain.rentalhouse.entity.PublicRentalHouse;
import com.example.itaminbackend.global.dto.PaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional(readOnly = true)
    public PaginationDto<List<PublicRentalHouseDto.WantedPublicRentalHouseResponse>> getWantedPublicRentalHouse(String sigunguCode, Pageable pageable){
        Page<PublicRentalHouseDto.WantedPublicRentalHouseResponse> page = this.publicRentalHouseRepository.findWantedPublicRentalHouse(sigunguCode, pageable);
        List<PublicRentalHouseDto.WantedPublicRentalHouseResponse> data = page.get().collect(Collectors.toList());
        return PaginationDto.of(page, data);
    }

    @Override
    public PublicRentalHouseDto.PublicRentalHouseDetailResponse getPublicRentalHouseDetail(Long publicRentalHouseId){
        return this.publicRentalHouseRepository.findPublicRentalHouseDetail(publicRentalHouseId);
    }
}
