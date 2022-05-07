package com.example.itaminbackend.domain.rentalhouse.controller;

import com.example.itaminbackend.domain.rentalhouse.constant.PublicRentalHouseConstants.EPublicRentalHouseResponseMessage;
import com.example.itaminbackend.domain.rentalhouse.dto.PublicRentalHouseDto.PublicRentalHouseCountResponse;
import com.example.itaminbackend.domain.rentalhouse.service.PublicRentalHouseService;
import com.example.itaminbackend.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
