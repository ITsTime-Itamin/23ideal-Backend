package com.example.itaminbackend.domain.rentalhouse.service;

import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;
import com.example.itaminbackend.domain.rentalhouse.entity.PublicRentalHouse;
import com.example.itaminbackend.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicRentalHouseService {
    void saveAll(List<PublicRentalHouse> publicRentalHouseList);
    void deleteAll();
    PublicRentalHouseCountResponse getPublicRentalHouseCount();
    PaginationDto<List<PublicRentalHouseDto.WantedPublicRentalHouseResponse>> getWantedPublicRentalHouse(String sigunguCode, Pageable pageable);
    PublicRentalHouseDto.PublicRentalHouseDetailResponse getPublicRentalHouseDetail(Long publicRentalHouseId);
}
