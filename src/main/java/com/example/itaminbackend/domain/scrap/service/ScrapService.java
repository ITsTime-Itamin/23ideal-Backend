package com.example.itaminbackend.domain.scrap.service;

import com.example.itaminbackend.domain.scrap.dto.ScrapDto.BoardInquiryByScrapRankingResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.ScrapCountResponse;
import com.example.itaminbackend.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScrapService {

    ScrapCountResponse getScrapCount(Long boardId);
//    ScrapChangeResponse changeScrap(ScrapChangeRequest scrapChangeRequest, Long userId);
//    ScrapOrNotResponse getScrapOrNot(ScrapDto.ScrapOrNotRequest scrapOrNotRequest, Long userId);
    PaginationDto<List<BoardInquiryByScrapRankingResponse>> getBoardsByScrapRanking(Pageable pageable);

}
