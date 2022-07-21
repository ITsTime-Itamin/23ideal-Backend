package com.example.itaminbackend.domain.rentalhouse.controller;

import com.example.itaminbackend.domain.board.constant.BoardConstants;
import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.rentalhouse.constant.PublicRentalHouseConstants.EPublicRentalHouseResponseMessage;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseDetailResponse;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.WantedPublicRentalHouseResponse;
import com.example.itaminbackend.domain.rentalhouse.service.PublicRentalHouseService;
import com.example.itaminbackend.global.dto.PaginationDto;
import com.example.itaminbackend.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/houses")
@Api(tags = "House API")
public class PublicRentalHouseApiController {

    private final PublicRentalHouseService publicRentalHouseService;

    @ApiOperation(value = "서울 모든 구의 공공임대주택 개수 가져오기", notes = "서울 모든 구의 공공임대주택 개수 가져옵니다.")
    @GetMapping
    public ResponseEntity<ResponseDto<PublicRentalHouseCountResponse>> getPublicRentalHouseCount(){
        return ResponseEntity.ok(ResponseDto.create(EPublicRentalHouseResponseMessage.eGetPublicRentalHouseSuccess.getMessage()
                , publicRentalHouseService.getPublicRentalHouseCount()));
    }

    @ApiOperation(value="원하는 구의 공고 불러오기", notes="원하는 구의 공고 불러오기")
    @GetMapping("/{signguCode}")
    public ResponseEntity<ResponseDto<PaginationDto<List<WantedPublicRentalHouseResponse>>>> getWantedPublicRentalHouse(@PathVariable String signguCode, @PageableDefault Pageable pageable){
        return ResponseEntity.ok(ResponseDto.create(EPublicRentalHouseResponseMessage.eGetWantedPublicRentalHouseSuccess.getMessage()
                , publicRentalHouseService.getWantedPublicRentalHouse(signguCode,pageable)));
    }

    @ApiOperation(value="원하는 공고 자세히 보기", notes="원하는 공고 자세히 보기")
    @GetMapping("/{publicRentalHouseId}")
    public ResponseEntity<ResponseDto<PublicRentalHouseDetailResponse>> getDetailPublicRentalHouse(@PathVariable Long publicRentalHouseId){
        return ResponseEntity.ok(ResponseDto.create(EPublicRentalHouseResponseMessage.eGetPublicRentalHouseDetailSuccess.getMessage(),
                publicRentalHouseService.getPublicRentalHouseDetail(publicRentalHouseId)));
    }
}
