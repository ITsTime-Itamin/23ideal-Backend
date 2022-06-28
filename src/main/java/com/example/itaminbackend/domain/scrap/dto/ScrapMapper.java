package com.example.itaminbackend.domain.scrap.dto;

import com.example.itaminbackend.domain.board.dto.BoardMapperSupport;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.ScrapCountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ScrapMapperSupport.class)
public interface ScrapMapper {

    @Mapping(target = "scrapCount", source = "scrapCount")
    ScrapCountResponse toScrapCountResponse(Long scrapCount);
}
