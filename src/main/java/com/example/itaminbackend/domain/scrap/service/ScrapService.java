package com.example.itaminbackend.domain.scrap.service;

import com.example.itaminbackend.domain.scrap.dto.ScrapDto;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.*;
import com.example.itaminbackend.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScrapService {

    ScrapCountResponse getScrapCount(Long boardId);
    ScrapChangeResponse changeScrap(ScrapChangeRequest scrapChangeRequest);
    ScrapOrNotResponse getScrapOrNot(ScrapDto.ScrapOrNotRequest scrapOrNotRequest);
    PaginationDto<List<BoardInquiryByScrapRankingResponse>> getBoardsByScrapRanking(Pageable pageable);

}
