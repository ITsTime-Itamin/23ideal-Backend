package com.example.itaminbackend.domain.scrap.dto;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.ScrapChangeResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.ScrapCountResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.ScrapOrNotResponse;
import com.example.itaminbackend.domain.scrap.entity.Scrap;
import com.example.itaminbackend.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ScrapMapperSupport.class)
public interface ScrapMapper {

    @Mapping(target = "scrapCount", source = "scrapCount")
    ScrapCountResponse toScrapCountResponse(Long scrapCount);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "board", source = "board")
    Scrap toEntity(User user, Board board);

    @Mapping(target = "scrapId", source = "scrapId")
    @Mapping(target = "isCreated", source = "isCreated")
    ScrapChangeResponse toScrapChangeResponse(Long scrapId, Boolean isCreated);

    @Mapping(target = "isScraped", source = "isScraped")
    ScrapOrNotResponse toScrapOrNotResponse(Boolean isScraped);
}
